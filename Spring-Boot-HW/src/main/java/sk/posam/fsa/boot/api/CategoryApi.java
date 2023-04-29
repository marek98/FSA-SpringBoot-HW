package sk.posam.fsa.boot.api;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/v1")
public interface CategoryApi {

    @GetMapping("/categories/{id}")
    CategoryDto one(@PathVariable Long id);

    @GetMapping("/categories")
    Collection<CategoryDto> all(@RequestParam(name = "name", required = false) String nameFilter);

    @PostMapping("/categories")
    long newCategory(@RequestBody CategoryDto newCategory);

    @PutMapping("/categories/{id}")
    void replaceCategory(@RequestBody CategoryDto newCategory, @PathVariable Long id);

    @DeleteMapping("/categories/{id}")
    void deleteCategory(@PathVariable Long id);

    @GetMapping("/categories/{id}/films")
    Collection<FilmDto> films(@PathVariable Long id);
}
