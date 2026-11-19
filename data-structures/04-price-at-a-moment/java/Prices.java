// Drill 04: the price at a moment. Make PricesTest pass, fast.
// times[i] is when prices[i] took effect; times is sorted, oldest first.

public class Prices {
    /** The price in effect at time `at`: the last one that took effect at or before it. -1 if none had yet. */
    static long priceAt(long[] times, long[] prices, long at) {
        long found = -1;
        for (int i = 0; i < times.length; i++) {
            if (times[i] <= at) found = prices[i];
            else break;
        }
        return found;
    }
}
