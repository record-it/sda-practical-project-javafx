package pl.sda.api.swapi.mapper;

import pl.sda.api.swapi.domain.FilmDomain;
import pl.sda.api.swapi.model.Film;

import java.time.LocalDate;

public class FilmMapper {
    public static FilmDomain mapTo(Film film){
        return FilmDomain.builder()
                .title(film.getTitle())
                .director(film.getDirector())
                .releaseDate(LocalDate.parse(film.getRelease_date()))
                .build();
    }
}
