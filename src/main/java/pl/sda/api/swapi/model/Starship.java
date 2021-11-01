package pl.sda.api.swapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Starship {
    @JsonProperty("MGLT")
    private String MGLT;
    private String name;
    private String model;
    private String consumables;
    private String starship_class;
    private String manufacturer;
    private String cost_in_credits;
    private String length;
    private String crew;
    private String passengers;
    private String max_atmosphering_speed;
    private String hyperdrive_rating;
    private String cargo_capacity;
    private List<String> films;
    private List<String> pilots;
    private String url;
    private String created;
    private String edited;
}
