package pl.sda.mapper;

import pl.sda.api.swapi.model.*;
import pl.sda.domain.DomainFilm;
import pl.sda.domain.DomainPerson;

import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class PersonMapper {
    public static DomainPerson mapTo(Person person, List<DomainFilm> films, Planet homeworld){
        return DomainPerson.builder()
                .name(person.getName())
                .mass(Integer.parseInt(person.getMass()))
                .created(Date.from(Instant.parse(person.getCreated())))
                .edited(Date.from(Instant.parse(person.getEdited())))
                .films(films)
                .hairColor(person.getHair_color())
                .height(Integer.parseInt(person.getHeight()))
                .skinColor(person.getSkin_color())
                .gender(person.getGender())
                .url(URI.create(person.getUrl()))
                .build();
    }
}
