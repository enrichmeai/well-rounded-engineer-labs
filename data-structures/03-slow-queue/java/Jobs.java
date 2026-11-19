// Drill 03: the slow queue. Make QueueTest pass, fast. Jobs must come out oldest first.

import java.util.ArrayList;
import java.util.List;

public class Jobs {
    static int handle(int job) {
        return job * 2;             // stands in for real work; it is not the slow part
    }

    static List<Integer> processAll(List<Integer> jobs) {
        List<Integer> queue = new ArrayList<>(jobs);
        List<Integer> done = new ArrayList<>();
        while (!queue.isEmpty()) {
            int job = queue.remove(0);      // oldest first
            done.add(handle(job));
        }
        return done;
    }
}
