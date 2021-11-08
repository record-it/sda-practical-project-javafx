package pl.sda.api.nbp;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CurrencyTable {
    private String table;
    private String no;
    private String effectiveDate;
    private List<Rate> rates;

    public LocalDate getEffectiveDate(){
        return LocalDate.parse(effectiveDate);
    }
}
