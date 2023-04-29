package sk.posam.fsa.boot.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.posam.fsa.boot.api.CategoryDto;
import sk.posam.fsa.boot.api.FilmDto;
import sk.posam.fsa.boot.application.assembler.CategoryAssembler;
import sk.posam.fsa.boot.application.assembler.FilmAssembler;
import sk.posam.fsa.boot.application.repositories.CategoryCrudRepository;
import sk.posam.fsa.boot.controller.EntityNotFoundException;
import sk.posam.fsa.boot.domain.Category;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class CategoryApiServiceImpl implements CategoryApiService {
    @Autowired
    private CategoryCrudRepository categoryRepositary;

    private CategoryAssembler categoryAssembler = new CategoryAssembler();

    private FilmAssembler filmAssembler = new FilmAssembler();

    @Override
    public CategoryDto getCategoryById(long categoryId) {
        Optional<Category> category =  categoryRepositary.findById(categoryId);

        return category.map(categoryAssembler::toDto)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Collection<CategoryDto> findCategoriesByName(String name) {
        if(name == null || name.isBlank()) {
            return categoryAssembler.toDto(categoryRepositary.findAll());
        }
        else {
            return categoryAssembler.toDto(categoryRepositary.findByName(name));
        }
    }

    @Override
    public long createNewCategory(CategoryDto dto) {
        dto.lastUpdated = new Date();
        Category category = categoryRepositary.save(categoryAssembler.fromDto(dto));

        return category.getId();
    }

    @Override
    public void replaceCategory(CategoryDto dto, long id) {
        Optional<Category> categoryResult = categoryRepositary.findById(id);
        Category category;

        if(categoryResult.isPresent()) {
            category = categoryResult.get();
            category.setName(dto.name);
            category.setFilms(filmAssembler.fromDto(dto.films));
            category.setLastUpdated(new Date());
        }
        else {
            dto.id = id;
            category = categoryAssembler.fromDto(dto);
        }

        categoryRepositary.save(category);
    }

    @Override
    public void deleteCategory(long categoryId) {
        categoryRepositary.deleteById(categoryId);
    }

    @Override
    public Collection<FilmDto> getCategoryFilms(long categoryId) {
        Optional<Category> categoryResult = categoryRepositary.findById(categoryId);

        return categoryResult.map((category -> filmAssembler.toDto(category.getFilms())))
                .orElseThrow(() -> new EntityNotFoundException());
    }
}
