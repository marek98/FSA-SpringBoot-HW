package sk.posam.fsa.boot.application.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.fsa.boot.api.ActorDto;
import sk.posam.fsa.boot.api.FilmDto;
import sk.posam.fsa.boot.application.assembler.ActorAssembler;
import sk.posam.fsa.boot.application.assembler.FilmAssembler;
import sk.posam.fsa.boot.application.repositories.ActorCrudRepository;
import sk.posam.fsa.boot.domain.Actor;
import sk.posam.fsa.boot.domain.services.FilmArchiveService;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class ActorApiServiceImpl implements ActorApiService {
    @Autowired
    private ActorCrudRepository actorRepository;

    @Autowired
    private FilmArchiveService filmArchiveService;

    private ActorAssembler actorAssembler = new ActorAssembler();

    private FilmAssembler filmAssembler = new FilmAssembler();

    @Override
    public ActorDto getActorById(long actorId) {
        Optional<Actor> actor = actorRepository.findById(actorId);

        return actor.map(actorAssembler::toDto)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Collection<ActorDto> findActorsByFirstName(String firstName) {
        if(firstName == null || firstName.isBlank()) {
            return actorAssembler.toDto(actorRepository.findAll());
        }
        else {
            return actorAssembler.toDto(actorRepository.findByFirstName(firstName));
        }
    }

    @Override
    public Collection<ActorDto> findActorsByLastName(String lastName) {
        if(lastName == null || lastName.isBlank()) {
            return actorAssembler.toDto(actorRepository.findAll());
        }
        else {
            return actorAssembler.toDto(actorRepository.findByLastName(lastName));
        }
    }

    @Override
    public Collection<ActorDto> findActorsByFirstNameAndLastName(String firstName, String lastName) {
        if(firstName == null || firstName.isBlank()) {
            return this.findActorsByLastName(lastName);
        }
        if(lastName == null || lastName.isBlank()) {
            return this.findActorsByFirstName(firstName);
        }

        return actorAssembler.toDto(actorRepository.findByFirstNameAndLastName(firstName, lastName));
    }

    @Override
    public long createNewActor(ActorDto dto) {
        dto.lastUpdated = new Date();
        Actor newActor = actorRepository.save(actorAssembler.fromDto(dto));

        return newActor.getId();
    }

    @Override
    public void replaceActor(ActorDto dto, long actorId) {
        Optional<Actor> actorResult = actorRepository.findById(actorId);
        Actor actor;

        if(actorResult.isPresent()) {
            actor = actorResult.get();
            actor.setFirstName(dto.firstName);
            actor.setLastName(dto.lastName);
            actor.setLastUpdated(new Date());
        }
        else {
            dto.id = actorId;
            actor = actorAssembler.fromDto(dto);
        }

        actorRepository.save(actor);
    }

    @Override
    public void deleteActor(long actorId) {
        actorRepository.deleteById(actorId);
    }

    @Override
    public boolean wasActorInFilm(long actorId, long filmId) {
        return filmArchiveService.wasInFilm(actorId, filmId);
    }

    @Override
    public Collection<FilmDto> getActorFilms(long actorId) {
        Optional<Actor> actorResult = actorRepository.findById(actorId);

        return actorResult.map((actor) -> filmAssembler.toDto(actor.getFilms()))
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
