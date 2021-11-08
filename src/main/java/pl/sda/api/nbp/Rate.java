package pl.sda.api.nbp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    private String currency;
    private String code;
    private double mid;

    @Override
    public String toString(){
        return currency + " - " + code;
    }
}
