package sk.posam.fsa.boot.application.assembler;

import sk.posam.fsa.boot.api.FilmDto;
import sk.posam.fsa.boot.domain.Film;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FilmAssembler {

    public Set<FilmDto> toDto(Iterable<Film> films) {
        return StreamSupport.stream(films.spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    public FilmDto toDto(Film film) {
        FilmDto result = new FilmDto();

        result.id = film.getId();
        result.title = film.getTitle();
        result.description = film.getDescription();
        result.releaseYear = film.getReleaseYear();
        result.length = film.getLength();
        result.actors = new ActorAssembler().toDto(film.getActors());
        //result.categories = new CategoryAssembler().toDto(film.getCategories());
        result.lastUpdated = film.getLastUpdated();

        return result;
    }

    public Set<Film> fromDto(Set<FilmDto> dtos) {
        return dtos.stream()
                .map(this::fromDto)
                .collect(Collectors.toSet());
    }

    public Film fromDto(FilmDto dto) {
        Film result = new Film();

        result.setId(dto.id);
        result.setTitle(dto.title);
        result.setDescription(dto.description);
        result.setReleaseYear(dto.releaseYear);
        result.setLength(dto.length);
        result.setActors(new ActorAssembler().fromDto(dto.actors));
        result.setCategories(new CategoryAssembler().fromDto(dto.categories));
        result.setLastUpdated(dto.lastUpdated);

        return result;
    }
}
