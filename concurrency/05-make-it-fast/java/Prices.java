// Drill 05: make it fast. Make PricesTest pass. Do not change fetchPrice().

import java.util.ArrayList;
import java.util.List;

public class Prices {

    /** Stands in for a call to a price service: 0.1 seconds of waiting on the network. */
    static int fetchPrice(String sku) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 100 + Integer.parseInt(sku.substring("sku-".length()));
    }

    static List<Integer> fetchAll(List<String> skus) throws Exception {
        List<Integer> out = new ArrayList<>();
        for (String sku : skus) out.add(fetchPrice(sku));
        return out;
    }
}
