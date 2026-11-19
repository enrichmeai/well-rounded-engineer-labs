import java.util.Random;

public class PricesTest {
    public static void main(String[] args) throws InterruptedException {
        long[] t = {10, 20, 30}, p = {100, 120, 90};
        Check.equal("before the first price", -1L, Prices.priceAt(t, p, 5));
        Check.equal("exactly when it took effect", 100L, Prices.priceAt(t, p, 10));
        Check.equal("in between", 120L, Prices.priceAt(t, p, 25));
        Check.equal("after the last", 90L, Prices.priceAt(t, p, 99));

        int n = 1_000_000;
        long[] times = new long[n], prices = new long[n];
        for (int i = 0; i < n; i++) {
            times[i] = i * 10L;
            prices[i] = 1000 + i;
        }
        Random rng = new Random(1);
        long[] moments = new long[50_000];
        for (int i = 0; i < moments.length; i++) moments[i] = (long) (rng.nextDouble() * n * 10);
        long[] result = Timed.within("50,000 refunds", 1.0, () -> {
            long[] out = new long[moments.length];
            for (int i = 0; i < moments.length; i++) out[i] = Prices.priceAt(times, prices, moments[i]);
            return out;
        });
        if (result != null) {
            boolean right = true;
            for (int i = 0; i < moments.length; i++) right &= result[i] == 1000 + moments[i] / 10;
            Check.isTrue("every refund gets the right price", right);
        }
        Check.done();
    }
}
