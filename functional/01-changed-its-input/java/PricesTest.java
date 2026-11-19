import java.util.ArrayList;
import java.util.List;

public class PricesTest {
    public static void main(String[] args) {
        Check.equal("with tax", List.of(12.0, 24.0), Prices.withTax(new ArrayList<>(List.of(10.0, 20.0)), 0.2));
        Check.equal("cheapest three", List.of(1.0, 2.0, 3.0), Prices.cheapestThree(new ArrayList<>(List.of(5.0, 1.0, 4.0, 2.0, 3.0))));

        List<Double> basket = new ArrayList<>(List.of(10.0, 20.0, 5.0, 1.0));
        Prices.withTax(basket, 0.2);
        Prices.cheapestThree(basket);
        Check.equal("the caller's list is left alone", List.of(10.0, 20.0, 5.0, 1.0), basket);

        List<Double> again = new ArrayList<>(List.of(10.0, 20.0));
        Check.equal("same input, same answer", Prices.withTax(again, 0.2), Prices.withTax(again, 0.2));
        Check.done();
    }
}
