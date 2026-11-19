// Drill 02: the key that cannot be found. Make StockTest pass. Keep SkuKey as the map key.

import java.util.HashMap;
import java.util.Map;

public class Stock {

    static final class SkuKey {
        final String sku;
        final int size;

        SkuKey(String sku, int size) {
            this.sku = sku;
            this.size = size;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof SkuKey k && k.sku.equals(sku) && k.size == size;
        }
    }

    final Map<SkuKey, Integer> counts = new HashMap<>();

    void add(String sku, int size, int n) {
        counts.merge(new SkuKey(sku, size), n, Integer::sum);
    }

    int inStock(String sku, int size) {
        return counts.getOrDefault(new SkuKey(sku, size), 0);
    }
}
