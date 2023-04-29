package sk.posam.fsa.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.fsa.boot.api.ActorApi;
import sk.posam.fsa.boot.api.ActorDto;
import sk.posam.fsa.boot.api.FilmDto;
import sk.posam.fsa.boot.application.services.ActorApiService;

import java.util.Collection;

@RestController
public class ActorApiController implements ActorApi {

    @Autowired
    private ActorApiService apiService;

    @Override
    public ActorDto one(Long id) {
        return apiService.getActorById(id);
    }

    @Override
    public Collection<ActorDto> all(String firstNameFilter, String lastNameFilter) {
        return apiService.findActorsByFirstNameAndLastName(firstNameFilter, lastNameFilter);
    }

    @Override
    public long newActor(ActorDto newActor) {
        return apiService.createNewActor(newActor);
    }

    @Override
    public void replaceActor(ActorDto newActor, Long id) {
        apiService.replaceActor(newActor, id);
    }

    @Override
    public void deleteActor(Long id) {
        apiService.deleteActor(id);
    }

    @Override
    public boolean wasInFilm(long actorId, long filmId) {
        return apiService.wasActorInFilm(actorId, filmId);
    }

    @Override
    public Collection<FilmDto> films(long actorId) {
        return apiService.getActorFilms(actorId);
    }
}
