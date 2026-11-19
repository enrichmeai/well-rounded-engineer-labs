import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BackoffTest {
    public static void main(String[] args) {
        List<Double> delays = Backoff.backoffDelays(8, new Random(1), 0.1, 5.0);
        Check.equal("one wait per attempt", 8, delays.size());
        boolean within = true;
        for (int n = 0; n < delays.size(); n++) within &= delays.get(n) >= 0 && delays.get(n) <= Math.min(5.0, 0.1 * Math.pow(2, n));
        Check.isTrue("every wait is within its ceiling", within);

        double first = 0, later = 0;
        Random clientSeeds = new Random(7);
        for (int client = 0; client < 1000; client++) {
            List<Double> c = Backoff.backoffDelays(7, new Random(clientSeeds.nextLong()), 0.1, 5.0);
            first += c.get(0);
            later += c.get(6);
        }
        Check.isTrue("later retries wait much longer on average", later > 4 * first);

        // Each client gets its own random generator, seeded from one source. (Seeding them new Random(0),
        // new Random(1), ... instead makes their first numbers nearly equal: java.util.Random's first output
        // barely changes between consecutive small seeds. Measured here: 724 of 1,000 clients in one window.)
        Random seeds = new Random(42);
        Map<Long, Integer> perWindow = new HashMap<>();             // retries per 10 ms window
        for (int client = 0; client < 1000; client++) {
            double t = Backoff.backoffDelays(1, new Random(seeds.nextLong()), 0.1, 5.0).get(0);
            perWindow.merge((long) (t * 1000) / 10, 1, Integer::sum);
        }
        int busiest = perWindow.values().stream().max(Integer::compare).orElse(0);
        Check.isTrue("no more than 250 of 1,000 clients retry in the same 10 ms (busiest: " + busiest + ")", busiest <= 250);
        Check.done();
    }
}
