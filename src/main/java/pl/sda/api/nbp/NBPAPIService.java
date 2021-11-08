package pl.sda.api.nbp;

import pl.sda.api.lib.APISimpleGenericRepository;
import pl.sda.api.lib.GenericSimpleRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class NBPAPIService implements NBPService{
    private static final URI NBP_URI = URI.create("http://api.nbp.pl/api/exchangerates/tables/A?format=json");
    private GenericSimpleRepository<CurrencyTable[]> rateRepository =
            new APISimpleGenericRepository<>(CurrencyTable[].class);
    @Override
    public double calculate(Rate sourceRate, double amount, Rate targetRate) {
        return sourceRate.getMid() * amount / targetRate.getMid();
    }

    @Override
    public List<Rate> getRates() throws ApiConnectionException{
        try {
            final Optional<CurrencyTable[]> optionalCurrencyTables =
                    rateRepository.findByURI(NBP_URI);
            //dopisz kod zwracający pole rates z obiektu klasy CurrencyTable
            //1. sprawdz czy w optionalCurrencyTables jest tablica
            //2. Jeśli jest to wyciągnij z optionala tablicę
            //3. W tablicy odwołaj się do pierwszej komórki
            //4. Z obiektu z pierwszej komórki zwróć pole rates
//            return optionalCurrencyTables.filter(table -> table.length == 1)
//                    .map(table -> table[0].getRates()).orElse(Collections.emptyList());
            if (optionalCurrencyTables.isPresent()){
                final CurrencyTable[] currencyTables = optionalCurrencyTables.get();
                if (currencyTables.length == 1){
                    final CurrencyTable currencyTable = currencyTables[0];
                    currencyTable.getRates().add(
                            Rate.builder()
                                    .code("PLN")
                                    .currency("złoty")
                                    .mid(1)
                                    .build()
                    );
                    return  currencyTable.getRates();
                } else {
                    return Collections.emptyList();
                }
            } else {
                return Collections.emptyList();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            throw new ApiConnectionException("Problem z połączeniem z serwisem NBP");
        }
    }
}
