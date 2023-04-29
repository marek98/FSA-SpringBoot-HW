package sk.posam.fsa.boot.application.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.posam.fsa.boot.domain.Category;
import sk.posam.fsa.boot.domain.repositories.CategoryRepository;

import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private CategoryCrudRepository categoryCrudRepository;

    @Override
    public Category get(long id) {
        Optional<Category> category = categoryCrudRepository.findById(id);

        return category.get();
    }
}
