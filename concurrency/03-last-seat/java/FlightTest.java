import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class FlightTest {
    public static void main(String[] args) throws Exception {
        Flight one = new Flight(1);
        Check.isTrue("first booking succeeds", one.book("Ana"));
        Check.isTrue("second booking is refused", !one.book("Ben"));

        Flight f = new Flight(10);
        CyclicBarrier start = new CyclicBarrier(40);
        AtomicInteger sold = new AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            int n = i;
            threads.add(Thread.ofPlatform().start(() -> {
                try {
                    start.await();                 // everyone presses at the same moment
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
                if (f.book("passenger-" + n)) sold.incrementAndGet();
            }));
        }
        for (Thread t : threads) t.join();
        Check.equal("seats sold on a flight with 10", 10, sold.get());
        synchronized (f) {
            Check.equal("seats left", 0, f.seatsLeft);
        }
        Check.equal("passengers on the list", 10, f.passengers.size());
        Check.done();
    }
}
