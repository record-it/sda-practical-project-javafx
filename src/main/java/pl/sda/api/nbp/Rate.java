package pl.sda.api.nbp;

import lombok.Builder;
import lombok.Data;

@Data
public class Rate {
    private String currency;
    private String code;
    private double mid;
}
