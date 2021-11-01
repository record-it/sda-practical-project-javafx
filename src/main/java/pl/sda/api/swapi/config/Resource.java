package pl.sda.api.swapi.config;

import pl.sda.api.swapi.model.*;

import java.net.URI;

public enum Resource {
    PLANETS(Planet.class),
    FILMS(Film.class),
    PEOPLE(Person.class),
    STARSHIPS(Starship.class),
    VEHICLES(Vehicle.class),
    SPECIES(Species.class);
    private static final URI BASE_URI = URI.create("https://swapi.dev/api/");

    private final Class clazz;

    Resource(Class clazz) {
        this.clazz = clazz;
    }

    public URI toURI(){
        return BASE_URI.resolve(BASE_URI.getPath() + "/" + this.name().toLowerCase()).normalize();
    }

    public  Class getClazz() {
        return clazz;
    }
}
