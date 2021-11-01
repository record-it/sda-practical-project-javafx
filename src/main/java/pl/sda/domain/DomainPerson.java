package pl.sda.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pl.sda.api.swapi.model.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class DomainPerson {
    private String name;
    private String birthYear;
    private String eyeColor;
    private String gender;
    private String hairColor;
    private int height;
    private int mass;
    private String skinColor;
    private Planet homeworld;
    private List<DomainFilm> films;
    private URI url;
    private Date created;
    private Date edited;
}
