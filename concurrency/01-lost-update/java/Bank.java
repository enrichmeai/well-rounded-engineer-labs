// Drill 01: the lost update. Make BankTest pass. Keep the call to recordAuditEntry().

import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    static final AtomicLong auditEntries = new AtomicLong();

    /** Stands in for slow work between the read and the write: a database call, a log line. */
    static void recordAuditEntry() {
        auditEntries.incrementAndGet();
        Thread.yield();                 // gives other threads a chance to run here, as real I/O does
    }

    static class Account {
        private int balance;

        Account(int balance) {
            this.balance = balance;
        }

        int balance() {
            return balance;
        }

        void deposit(int amount) {
            int seen = balance;
            recordAuditEntry();
            balance = seen + amount;
        }
    }
}
