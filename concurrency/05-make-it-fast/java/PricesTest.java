import java.util.ArrayList;
import java.util.List;

public class PricesTest {
    public static void main(String[] args) throws Exception {
        List<String> skus = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            skus.add("sku-" + i);
            expected.add(100 + i);
        }
        long start = System.nanoTime();
        List<Integer> result = Prices.fetchAll(skus);
        double seconds = (System.nanoTime() - start) / 1e9;
        Check.equal("same prices, same order", expected, result);
        Check.isTrue(String.format("under 0.5 s (took %.2f s)", seconds), seconds < 0.5);
        Check.done();
    }
}
