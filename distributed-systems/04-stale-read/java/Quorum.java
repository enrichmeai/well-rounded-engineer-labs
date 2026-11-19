// Drill 04: the stale read. Make QuorumTest pass.

import java.util.List;

public class Quorum {
    static final int N = 3;
    static final int W = 1;   // replicas that must confirm a write
    static final int R = 1;   // replicas asked on a read

    static class Replica {
        String value;
        int version;

        Replica(String value, int version) {
            this.value = value;
            this.version = version;
        }
    }

    /** Write to the reachable replicas. The write succeeds only if at least W of them confirmed it. */
    static boolean write(List<Replica> replicas, List<Integer> reachable, String value, int version) {
        if (reachable.size() < W) return false;
        for (int i : reachable) {
            replicas.get(i).value = value;
            replicas.get(i).version = version;
        }
        return true;
    }

    /** Read from R replicas and return the current value. */
    static String read(List<Replica> asked) {
        return asked.get(0).value;
    }
}
