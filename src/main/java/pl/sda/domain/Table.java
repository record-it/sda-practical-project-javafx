package pl.sda.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table{
    private String table;
    private String no;
    private String effectiveDate;
    private List<Rate> rates;
}
