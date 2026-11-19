// Drill 01: the function that changed its input. Make PricesTest pass.

import java.util.Collections;
import java.util.List;

public class Prices {
    static List<Double> withTax(List<Double> prices, double rate) {
        for (int i = 0; i < prices.size(); i++) {
            prices.set(i, Math.round(prices.get(i) * (1 + rate) * 100) / 100.0);
        }
        return prices;
    }

    static List<Double> cheapestThree(List<Double> prices) {
        Collections.sort(prices);
        return prices.subList(0, Math.min(3, prices.size()));
    }
}
