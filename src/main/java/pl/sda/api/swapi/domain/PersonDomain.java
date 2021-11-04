package pl.sda.api.swapi.domain;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class PersonDomain {
    private final String name;
    private final Integer height;
    private final Double mass;
    private final List<FilmDomain> films;

    public boolean isKnownHeight(){
        return height != null;
    }

    public boolean isKnownMass(){
        return  mass != null;
    }
}
