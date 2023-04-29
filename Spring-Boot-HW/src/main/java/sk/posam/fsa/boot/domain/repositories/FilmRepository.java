package sk.posam.fsa.boot.domain.repositories;

import sk.posam.fsa.boot.domain.Film;

public interface FilmRepository {
    Film get(long id);
}
