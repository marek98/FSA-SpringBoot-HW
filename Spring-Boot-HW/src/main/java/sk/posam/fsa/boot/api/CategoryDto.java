package sk.posam.fsa.boot.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;

public class CategoryDto {

    public Long id;

    public String name;

    @JsonIgnore
    public Set<FilmDto> films;

    public Date lastUpdated;

}
