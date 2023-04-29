package sk.posam.fsa.boot.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;

public class FilmDto {

    public Long id;

    public String title;

    public String description;

    public Integer releaseYear;

    public Integer length;

    @JsonIgnore
    public Set<ActorDto> actors;

    @JsonIgnore
    public Set<CategoryDto> categories;

    public Date lastUpdated;

}
