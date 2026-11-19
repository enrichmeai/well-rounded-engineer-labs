// Drill 02: the deadlock. Make TransfersTest pass. Keep one lock per account.

public class Transfers {

    /** Stands in for slow work while the first lock is held: writing an audit row. */
    static void recordAuditEntry() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static class Account {
        final int id;
        int balance;

        Account(int id, int balance) {
            this.id = id;
            this.balance = balance;
        }
    }

    static void transfer(Account source, Account target, int amount) {
        synchronized (source) {
            recordAuditEntry();
            synchronized (target) {
                source.balance -= amount;
                target.balance += amount;
            }
        }
    }
}
