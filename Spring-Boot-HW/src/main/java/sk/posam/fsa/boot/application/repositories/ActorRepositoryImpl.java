package sk.posam.fsa.boot.application.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.posam.fsa.boot.domain.Actor;
import sk.posam.fsa.boot.domain.repositories.ActorRepository;

import java.util.Optional;

@Repository
public class ActorRepositoryImpl implements ActorRepository {
    @Autowired
    private ActorCrudRepository actorCrudRepository;

    @Override
    public Actor get(long id) {
        Optional<Actor> actor = actorCrudRepository.findById(id);

        return actor.get();
    }
}
