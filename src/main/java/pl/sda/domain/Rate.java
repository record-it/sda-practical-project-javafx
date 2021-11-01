package pl.sda.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    private String currency;
    private String code;
    private double mid;

    @Override
    public String toString() {
        return code + " - " + currency;
    }
}
