package sk.posam.fsa.boot.application.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.posam.fsa.boot.domain.Film;
import sk.posam.fsa.boot.domain.repositories.FilmRepository;

import java.util.Optional;

@Repository
public class FilmRepositoryImpl implements FilmRepository {
    @Autowired
    private FilmCrudRepository filmCrudRepository;

    @Override
    public Film get(long id) {
        Optional<Film> film = filmCrudRepository.findById(id);

        return film.get();
    }
}
