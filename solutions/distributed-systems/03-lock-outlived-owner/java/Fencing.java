// Drill 03, solved: the storage refuses any write with a token older than one it has accepted.

public class Fencing {

    static class LockService {
        long lastToken = 0;

        long acquire() {
            return ++lastToken;
        }
    }

    static class Storage {
        String report;
        long highestToken = 0;

        boolean write(String report, long token) {
            if (token < highestToken) return false;   // from a lease that has since been replaced
            highestToken = token;
            this.report = report;
            return true;
        }
    }
}
