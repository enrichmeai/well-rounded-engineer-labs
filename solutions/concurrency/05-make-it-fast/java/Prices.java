// Drill 05, solved: one virtual thread per call, so the 20 waits overlap.

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Prices {

    static int fetchPrice(String sku) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 100 + Integer.parseInt(sku.substring("sku-".length()));
    }

    static List<Integer> fetchAll(List<String> skus) throws Exception {
        try (ExecutorService pool = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Integer>> futures = new ArrayList<>();
            for (String sku : skus) futures.add(pool.submit(() -> fetchPrice(sku)));
            List<Integer> out = new ArrayList<>();
            for (Future<Integer> f : futures) out.add(f.get());   // in submission order
            return out;
        }
    }
}
