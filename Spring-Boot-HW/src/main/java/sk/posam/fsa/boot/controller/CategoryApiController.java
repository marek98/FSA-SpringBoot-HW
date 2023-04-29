package sk.posam.fsa.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sk.posam.fsa.boot.api.CategoryApi;
import sk.posam.fsa.boot.api.CategoryDto;
import sk.posam.fsa.boot.api.FilmDto;
import sk.posam.fsa.boot.application.services.CategoryApiService;

import java.util.Collection;

@RestController
public class CategoryApiController implements CategoryApi {
    @Autowired
    private CategoryApiService categoryApi;


    @Override
    public CategoryDto one(Long id) {
        return categoryApi.getCategoryById(id);
    }

    @Override
    public Collection<CategoryDto> all(String nameFilter) {
        return categoryApi.findCategoriesByName(nameFilter);
    }

    @Override
    public long newCategory(CategoryDto newCategory) {
        return categoryApi.createNewCategory(newCategory);
    }

    @Override
    public void replaceCategory(CategoryDto newCategory, Long id) {
        categoryApi.replaceCategory(newCategory, id);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryApi.deleteCategory(id);
    }

    @Override
    public Collection<FilmDto> films(Long id) {
        return categoryApi.getCategoryFilms(id);
    }
}
