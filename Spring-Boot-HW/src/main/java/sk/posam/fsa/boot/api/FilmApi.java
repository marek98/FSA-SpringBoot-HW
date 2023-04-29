package sk.posam.fsa.boot.api;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/v1")
public interface FilmApi {

    @GetMapping("/films/{id}")
    FilmDto one(@PathVariable Long id);

    @GetMapping("/films")
    Collection<FilmDto> all(@RequestParam(name = "title", required = false) String titleFilter);

    @PostMapping("/films")
    long newFilm(@RequestBody FilmDto newFilm);

    @PutMapping("/films/{id}")
    void replaceFilm(@RequestBody FilmDto newFilm, @PathVariable Long id);

    @DeleteMapping("/films/{id}")
    void deleteFilm(@PathVariable Long id);

    @GetMapping("/films/{id}/actors")
    Collection<ActorDto> actors(@PathVariable Long id);

    @GetMapping("/films/{id}/categories")
    Collection<CategoryDto> categories(@PathVariable Long id);
}
