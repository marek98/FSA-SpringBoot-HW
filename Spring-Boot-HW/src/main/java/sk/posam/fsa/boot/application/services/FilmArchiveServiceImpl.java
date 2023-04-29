package sk.posam.fsa.boot.application.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.fsa.boot.domain.Actor;
import sk.posam.fsa.boot.domain.Film;
import sk.posam.fsa.boot.domain.repositories.ActorRepository;
import sk.posam.fsa.boot.domain.repositories.FilmRepository;
import sk.posam.fsa.boot.domain.services.FilmArchiveService;

@Service
public class FilmArchiveServiceImpl implements FilmArchiveService {
    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Override
    @Transactional
    public boolean wasInFilm(long actorId, long filmId) {
        Actor actor = actorRepository.get(actorId);
        Film film = filmRepository.get(filmId);

        return film.getActors().contains(actor);
    }
}
