package pl.sda.mapper;

import pl.sda.api.swapi.model.Film;
import pl.sda.domain.DomainFilm;

public class FilmMapper {
    public static DomainFilm mapTo(Film film){
        return DomainFilm.builder()
                .title(film.getTitle())
                .producer(film.getProducer())
                .episodeId(film.getEpisode_id())
                .openingCrawl(film.getOpening_crawl())
                .build();
    }
}
