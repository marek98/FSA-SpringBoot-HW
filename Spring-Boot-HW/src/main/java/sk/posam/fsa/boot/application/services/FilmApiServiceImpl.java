package sk.posam.fsa.boot.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.fsa.boot.api.ActorDto;
import sk.posam.fsa.boot.api.CategoryDto;
import sk.posam.fsa.boot.api.FilmDto;
import sk.posam.fsa.boot.application.assembler.ActorAssembler;
import sk.posam.fsa.boot.application.assembler.CategoryAssembler;
import sk.posam.fsa.boot.application.assembler.FilmAssembler;
import sk.posam.fsa.boot.application.repositories.FilmCrudRepository;
import sk.posam.fsa.boot.controller.EntityNotFoundException;
import sk.posam.fsa.boot.domain.Film;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class FilmApiServiceImpl implements FilmApiService {
    @Autowired
    private FilmCrudRepository filmRepositary;

    private FilmAssembler filmAssembler = new FilmAssembler();

    private ActorAssembler actorAssembler = new ActorAssembler();

    private CategoryAssembler categoryAssembler = new CategoryAssembler();

    @Override
    public FilmDto getFilmById(long filmId) {
        Optional<Film> film = filmRepositary.findById(filmId);

        return film.map(filmAssembler::toDto)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Collection<FilmDto> findFilmsByTitle(String title) {
        if(title == null || title.isBlank()) {
            return filmAssembler.toDto(filmRepositary.findAll());
        }
        else {
            return filmAssembler.toDto(filmRepositary.findFilmsByTitle(title));
        }
    }

    @Override
    public long createNewFilm(FilmDto dto) {
        dto.lastUpdated = new Date();
        Film newFilm = filmRepositary.save(filmAssembler.fromDto(dto));

        return newFilm.getId();
    }

    @Override
    public void replaceFilm(FilmDto dto, long filmId) {
        Optional<Film> filmResult = filmRepositary.findById(filmId);
        Film film;

        if(filmResult.isPresent()) {
            film = filmResult.get();
            film.setTitle(dto.title);
            film.setDescription(dto.description);
            film.setReleaseYear(dto.releaseYear);
            film.setLength(dto.length);
            film.setActors(actorAssembler.fromDto(dto.actors));
            film.setCategories(categoryAssembler.fromDto(dto.categories));
            film.setLastUpdated(new Date());
        }
        else {
            dto.id = filmId;
            film = filmAssembler.fromDto(dto);
        }

        filmRepositary.save(film);
    }

    @Override
    public void deleteFilm(long filmId) {
        filmRepositary.deleteById(filmId);
    }

    @Override
    public Collection<ActorDto> getFilmActors(long filmId) {
        Optional<Film> filmResult = filmRepositary.findById(filmId);

        return filmResult.map((film -> actorAssembler.toDto(film.getActors())))
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException());
    }

    @Override
    public Collection<CategoryDto> getFilmCategories(long filmId) {
        Optional<Film> filmResult = filmRepositary.findById(filmId);

        return filmResult.map((film) -> categoryAssembler.toDto(film.getCategories()))
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException());
    }
}
