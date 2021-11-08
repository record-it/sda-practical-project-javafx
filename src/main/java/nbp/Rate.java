package nbp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rate {
    private String currency;
    private String code;
    private double mid;
}
