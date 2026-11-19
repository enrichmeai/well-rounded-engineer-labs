// Drill 02: the retry storm. Make BackoffTest pass.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Backoff {
    /** How long to wait, in seconds, before each retry. */
    static List<Double> backoffDelays(int attempts, Random rng, double base, double cap) {
        List<Double> out = new ArrayList<>();
        for (int n = 0; n < attempts; n++) out.add(base);
        return out;
    }
}
