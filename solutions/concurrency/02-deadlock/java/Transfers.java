// Drill 02, solved: every transfer takes the two locks in the same order, by account id.

public class Transfers {

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
        Account first = source.id < target.id ? source : target;
        Account second = first == source ? target : source;
        synchronized (first) {
            recordAuditEntry();
            synchronized (second) {
                source.balance -= amount;
                target.balance += amount;
            }
        }
    }
}
