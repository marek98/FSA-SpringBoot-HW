package sk.posam.fsa.boot.domain.repositories;

import sk.posam.fsa.boot.domain.Category;

public interface CategoryRepository {
    Category get(long id);
}
