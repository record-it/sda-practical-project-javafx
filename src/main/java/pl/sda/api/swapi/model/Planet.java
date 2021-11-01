package pl.sda.api.swapi.model;

import lombok.Data;

import java.util.List;

@Data
public class Planet {
    private String rotation_period;
    private String name;
    private String diameter;
    private String orbital_period;
    private String surface_water;
    private String climate;
    private String gravity;
    private String terrain;
    private String population;
    private String created;
    private String edited;
    private String url;
    private List<String> films;
    private List<String> residents;
}
