package sk.posam.fsa.boot.application.repositories;

import org.springframework.data.repository.CrudRepository;
import sk.posam.fsa.boot.domain.Film;

import java.util.List;

public interface FilmCrudRepository extends CrudRepository<Film, Long> {
    List<Film> findFilmsByTitle(String title);
}
