package sk.posam.fsa.boot.application.services;

import sk.posam.fsa.boot.api.ActorDto;
import sk.posam.fsa.boot.api.FilmDto;

import java.util.Collection;

public interface ActorApiService {
    ActorDto getActorById(long actorId);

    Collection<ActorDto> findActorsByFirstName(String firstName);

    Collection<ActorDto> findActorsByLastName(String lastName);

    Collection<ActorDto> findActorsByFirstNameAndLastName(String firstName, String lastName);

    long createNewActor(ActorDto dto);

    void replaceActor(ActorDto dto, long actorId);

    void deleteActor(long actorId);

    boolean wasActorInFilm(long actorId, long filmId);

    Collection<FilmDto> getActorFilms(long actorId);
}
