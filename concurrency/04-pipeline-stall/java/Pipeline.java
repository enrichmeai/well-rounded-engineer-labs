// Drill 04: the pipeline that never finishes. Make PipelineTest pass.

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pipeline {

    record Order(int id, String item, int qty) {}

    record Result(String kind, int id, int total) {
        static Result ok(int id, int total) { return new Result("ok", id, total); }
        static Result failed(int id) { return new Result("failed", id, 0); }
    }

    static final Order STOP = new Order(-1, "", 0);
    static final Result DONE = new Result("done", -1, 0);

    static void worker(BlockingQueue<Order> inbox, BlockingQueue<Result> outbox, Map<String, Integer> prices)
            throws InterruptedException {
        while (true) {
            Order order = inbox.take();
            if (order == STOP) {
                outbox.put(DONE);
                return;
            }
            outbox.put(Result.ok(order.id(), prices.get(order.item()) * order.qty()));
        }
    }

    record Outcome(Map<Integer, Integer> totals, List<Integer> failed) {}

    static Outcome run(List<Order> orders, Map<String, Integer> prices, int workers) throws InterruptedException {
        BlockingQueue<Order> todo = new LinkedBlockingQueue<>(orders);
        BlockingQueue<Result> done = new LinkedBlockingQueue<>();
        for (int i = 0; i < workers; i++) todo.put(STOP);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < workers; i++) {
            threads.add(Thread.ofPlatform().start(() -> {
                try {
                    worker(todo, done, prices);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }));
        }
        Map<Integer, Integer> totals = new TreeMap<>();
        List<Integer> failed = new ArrayList<>();
        int stopped = 0;
        while (stopped < workers) {
            Result r = done.take();
            if (r == DONE) stopped++;
            else if (r.kind().equals("ok")) totals.put(r.id(), r.total());
            else failed.add(r.id());
        }
        for (Thread t : threads) t.join();
        failed.sort(null);
        return new Outcome(totals, failed);
    }
}
