// Drill 01, solved: both methods build new lists and never write to the one they were given.

import java.util.List;

public class Prices {
    static List<Double> withTax(List<Double> prices, double rate) {
        return prices.stream().map(p -> Math.round(p * (1 + rate) * 100) / 100.0).toList();
    }

    static List<Double> cheapestThree(List<Double> prices) {
        return prices.stream().sorted().limit(3).toList();
    }
}
