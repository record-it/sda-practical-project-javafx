package pl.sda.api.swapi.mapper;

import pl.sda.api.swapi.domain.FilmDomain;
import pl.sda.api.swapi.domain.PersonDomain;
import pl.sda.api.swapi.model.Person;

import java.util.List;

public class PersonMapper {
    public static PersonDomain mapTo(Person model, List<FilmDomain> films){
        Integer height;
        try {
            height = Integer.parseInt(model.getHeight());
        } catch (NumberFormatException e) {
            height = null;
        }
        Double mass = null;
        try {
            mass = Double.parseDouble(model.getHeight());
        } catch (NumberFormatException e) {
        }
        //zdefiniuj podobny kod dla pola mass
        return PersonDomain.builder()
                .films(films)
                .height(height)
                .mass(mass)
                .name(model.getName())
                .build();
    }
}
