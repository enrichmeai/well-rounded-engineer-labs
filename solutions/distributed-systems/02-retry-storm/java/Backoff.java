// Drill 02, solved: exponential backoff with full jitter.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Backoff {
    /** Attempt n waits a random time between 0 and min(cap, base * 2^n). */
    static List<Double> backoffDelays(int attempts, Random rng, double base, double cap) {
        List<Double> out = new ArrayList<>();
        for (int n = 0; n < attempts; n++) out.add(rng.nextDouble() * Math.min(cap, base * Math.pow(2, n)));
        return out;
    }
}
