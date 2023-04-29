package sk.posam.fsa.boot.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;

public class ActorDto {

    public Long id;

    public String firstName;

    public String lastName;

    @JsonIgnore
    public Set<FilmDto> films;

    public Date lastUpdated;

}
