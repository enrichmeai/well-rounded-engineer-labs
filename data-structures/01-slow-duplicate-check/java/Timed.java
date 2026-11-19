// Runs a piece of work on its own thread and gives up after a budget, so a slow answer fails instead of hanging.

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class Timed {
    static <T> T within(String what, double budgetSeconds, Supplier<T> work) throws InterruptedException {
        AtomicReference<T> box = new AtomicReference<>();
        long start = System.nanoTime();
        Thread t = new Thread(() -> box.set(work.get()));
        t.setDaemon(true);
        t.start();
        t.join((long) (budgetSeconds * 1000));
        double seconds = (System.nanoTime() - start) / 1e9;
        Check.isTrue(String.format("%s within %.1f s (took %s)", what, budgetSeconds,
                t.isAlive() ? "longer" : String.format("%.3f s", seconds)), !t.isAlive());
        return box.get();
    }
}
