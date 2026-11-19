// Drill 01, solved: deposit is synchronized, so the read and the write are one step.

import java.util.concurrent.atomic.AtomicLong;

public class Bank {

    static final AtomicLong auditEntries = new AtomicLong();

    static void recordAuditEntry() {
        auditEntries.incrementAndGet();
        Thread.yield();
    }

    static class Account {
        private int balance;

        Account(int balance) {
            this.balance = balance;
        }

        synchronized int balance() {
            return balance;
        }

        synchronized void deposit(int amount) {
            int seen = balance;
            recordAuditEntry();
            balance = seen + amount;
        }
    }
}
