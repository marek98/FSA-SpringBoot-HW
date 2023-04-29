package sk.posam.fsa.boot.application.repositories;

import org.springframework.data.repository.CrudRepository;
import sk.posam.fsa.boot.domain.Category;

import java.util.List;

public interface CategoryCrudRepository extends CrudRepository<Category, Long> {
    List<Category> findByName(String nameFilter);
}
