package sk.posam.fsa.boot.api;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/v1")
public interface ActorApi {

    @GetMapping("/actors/{id}")
    ActorDto one(@PathVariable Long id);

    @GetMapping("/actors")
    Collection<ActorDto> all(
            @RequestParam(name = "firstname", required = false) String firstNameFilter,
            @RequestParam(name = "lastname", required = false) String lastNameFilter
    );

    @PostMapping("/actors")
    long newActor(@RequestBody ActorDto newActor);

    @PutMapping("/actors/{id}")
    void replaceActor(@RequestBody ActorDto newActor, @PathVariable Long id);

    @DeleteMapping("/actors/{id}")
    void deleteActor(@PathVariable Long id);

    @GetMapping("/actors/{id}/in-film")
    boolean wasInFilm(
            @PathVariable(name = "id") long actorId,
            @RequestParam long filmId
    );

    @GetMapping("/actors/{id}/films")
    Collection<FilmDto> films(@PathVariable(name = "id") long actorId);
}
