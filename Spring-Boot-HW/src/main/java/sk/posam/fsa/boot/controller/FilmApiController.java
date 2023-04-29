package sk.posam.fsa.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.fsa.boot.api.ActorDto;
import sk.posam.fsa.boot.api.CategoryDto;
import sk.posam.fsa.boot.api.FilmApi;
import sk.posam.fsa.boot.api.FilmDto;
import sk.posam.fsa.boot.application.services.FilmApiService;

import java.util.Collection;

@RestController
public class FilmApiController implements FilmApi {
    @Autowired
    private FilmApiService filmApi;

    @Override
    public FilmDto one(Long id) {
        return filmApi.getFilmById(id);
    }

    @Override
    public Collection<FilmDto> all(String titleFilter) {
        return filmApi.findFilmsByTitle(titleFilter);
    }

    @Override
    public long newFilm(FilmDto newFilm) {
        return filmApi.createNewFilm(newFilm);
    }

    @Override
    public void replaceFilm(FilmDto newFilm, Long id) {
        filmApi.replaceFilm(newFilm, id);
    }

    @Override
    public void deleteFilm(Long id) {
        filmApi.deleteFilm(id);
    }

    @Override
    public Collection<ActorDto> actors(Long id) {
        return filmApi.getFilmActors(id);
    }

    @Override
    public Collection<CategoryDto> categories(Long id) {
        return filmApi.getFilmCategories(id);
    }


}
