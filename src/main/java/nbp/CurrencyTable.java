package nbp;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CurrencyTable {
    private String table;
    private String no;
    private LocalDate effectiveDate;
    private List<Rate> rates;
}
