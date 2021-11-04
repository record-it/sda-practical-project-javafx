package pl.sda.api.swapi.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FilmDomain {
    private String title;
    private String director;
    private LocalDate releaseDate;

    @Override
    public String toString(){
        return String.format("%40s%40s%12s", title, director, releaseDate.toString());
    }
}
