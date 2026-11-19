// Drill 03: the lock that outlived its owner. Make FencingTest pass.

public class Fencing {

    /** Hands out leases. Each new lease gets a fencing token one higher than the last. */
    static class LockService {
        long lastToken = 0;

        long acquire() {
            return ++lastToken;
        }
    }

    static class Storage {
        String report;

        /** Write the report under the lease with this fencing token. Returns whether the write was accepted. */
        boolean write(String report, long token) {
            this.report = report;
            return true;
        }
    }
}
