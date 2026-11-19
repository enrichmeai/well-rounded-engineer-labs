// Drill 02, solved: SkuKey hashes the same fields it compares.

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

        @Override
        public int hashCode() {
            return Objects.hash(sku, size);         // the same fields as equals
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
