package sk.posam.fsa.boot.domain.repositories;

import sk.posam.fsa.boot.domain.Actor;

public interface ActorRepository {
    Actor get(long id);
}
