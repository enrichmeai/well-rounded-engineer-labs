// Drill 03, solved: an ArrayDeque removes from the front in O(1).

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Jobs {
    static int handle(int job) {
        return job * 2;
    }

    static List<Integer> processAll(List<Integer> jobs) {
        ArrayDeque<Integer> queue = new ArrayDeque<>(jobs);
        List<Integer> done = new ArrayList<>();
        while (!queue.isEmpty()) {
            done.add(handle(queue.poll()));     // O(1): nothing else moves
        }
        return done;
    }
}
