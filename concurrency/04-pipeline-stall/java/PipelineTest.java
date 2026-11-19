import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class PipelineTest {
    static final Map<String, Integer> PRICES = Map.of("tea", 3, "cake", 5);

    static Pipeline.Outcome runWithTimeout(List<Pipeline.Order> orders) throws InterruptedException {
        AtomicReference<Pipeline.Outcome> box = new AtomicReference<>();
        Thread t = new Thread(() -> {
            try {
                box.set(Pipeline.run(orders, PRICES, 2));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        t.setDaemon(true);
        t.start();
        t.join(5_000);
        Check.isTrue("run() finishes within 5 seconds (a worker that dies never sends STOP)", !t.isAlive());
        return box.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Pipeline.Outcome good = runWithTimeout(List.of(new Pipeline.Order(1, "tea", 1), new Pipeline.Order(2, "cake", 2)));
        if (good != null) Check.equal("good orders priced", Map.of(1, 3, 2, 10), good.totals());

        Pipeline.Outcome mixed = runWithTimeout(List.of(new Pipeline.Order(1, "tea", 2),
                new Pipeline.Order(2, "scone", 1), new Pipeline.Order(3, "cake", 1)));
        if (mixed != null) {
            Check.equal("good orders still priced", Map.of(1, 6, 3, 5), mixed.totals());
            Check.equal("the unpriced order is reported", List.of(2), mixed.failed());
        }
        Check.done();
    }
}
