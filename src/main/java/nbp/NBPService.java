package nbp;

import java.io.IOException;
import java.util.List;

public interface NBPService {
    double calculate(Rate sourceRate, double amount, Rate targetRate);
    List<Rate> getRates() throws IOException, InterruptedException;
}
