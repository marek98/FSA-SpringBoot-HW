package sk.posam.fsa.boot.application.services;

import sk.posam.fsa.boot.api.CategoryDto;
import sk.posam.fsa.boot.api.FilmDto;

import java.util.Collection;

public interface CategoryApiService {

    CategoryDto getCategoryById(long categoryId);

    Collection<CategoryDto> findCategoriesByName(String name);

    long createNewCategory(CategoryDto dto);

    void replaceCategory(CategoryDto dto, long id);

    void deleteCategory(long categoryId);

    Collection<FilmDto> getCategoryFilms(long categoryId);
}
