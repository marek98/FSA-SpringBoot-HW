package sk.posam.fsa.boot.application.services;

import sk.posam.fsa.boot.api.ActorDto;
import sk.posam.fsa.boot.api.CategoryDto;
import sk.posam.fsa.boot.api.FilmDto;

import java.util.Collection;

public interface FilmApiService {
    FilmDto getFilmById(long filmId);

    Collection<FilmDto> findFilmsByTitle(String title);

    long createNewFilm(FilmDto dto);

    void replaceFilm(FilmDto dto, long filmId);

    void deleteFilm(long filmId);

    Collection<ActorDto> getFilmActors(long filmId);

    Collection<CategoryDto> getFilmCategories(long filmId);

}
