package sk.posam.fsa.boot.application.assembler;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import sk.posam.fsa.boot.api.CategoryDto;
import sk.posam.fsa.boot.domain.Category;

public class CategoryAssembler {

    public Set<CategoryDto> toDto(Iterable<Category> categories) {
        return StreamSupport.stream(categories.spliterator(), false)
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    public CategoryDto toDto(Category category) {
        CategoryDto result = new CategoryDto();

        result.id = category.getId();
        result.name = category.getName();
        result.films = new FilmAssembler().toDto(category.getFilms());
        result.lastUpdated = category.getLastUpdated();

        return result;
    }

    public Set<Category> fromDto(Set<CategoryDto> dtos) {
        return dtos.stream()
                .map(this::fromDto)
                .collect(Collectors.toSet());
    }

    public Category fromDto(CategoryDto dto) {
        Category result = new Category();

        result.setId(dto.id);
        result.setName(dto.name);
        result.setFilms( new FilmAssembler().fromDto(dto.films));
        result.setLastUpdated(dto.lastUpdated);

        return result;
    }
}
