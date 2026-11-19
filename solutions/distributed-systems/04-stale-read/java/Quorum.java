// Drill 04, solved: W + R > N, and a read keeps the answer with the highest version.

import java.util.Comparator;
import java.util.List;

public class Quorum {
    static final int N = 3;
    static final int W = 2;   // a majority must confirm a write
    static final int R = 2;   // ask a majority on a read

    static class Replica {
        String value;
        int version;

        Replica(String value, int version) {
            this.value = value;
            this.version = version;
        }
    }

    static boolean write(List<Replica> replicas, List<Integer> reachable, String value, int version) {
        if (reachable.size() < W) return false;
        for (int i : reachable) {
            replicas.get(i).value = value;
            replicas.get(i).version = version;
        }
        return true;
    }

    static String read(List<Replica> asked) {
        return asked.stream().max(Comparator.comparingInt(r -> r.version)).orElseThrow().value;
    }
}
