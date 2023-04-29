package sk.posam.fsa.boot.domain.services;

public interface FilmArchiveService {
    boolean wasInFilm(long actorId, long filmId);
}
