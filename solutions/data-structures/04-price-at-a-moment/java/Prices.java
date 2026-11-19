// Drill 04, solved: the times are sorted, so binary search finds the moment in O(log n).

import java.util.Arrays;

public class Prices {
    static long priceAt(long[] times, long[] prices, long at) {
        int i = Arrays.binarySearch(times, at);
        if (i < 0) i = -i - 2;                  // not found: -i - 1 is where it would go, so the one before it
        return i >= 0 ? prices[i] : -1;
    }
}
