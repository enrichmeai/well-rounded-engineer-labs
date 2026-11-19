import java.util.ArrayList;
import java.util.List;

public class BankTest {
    public static void main(String[] args) throws InterruptedException {
        Bank.Account single = new Bank.Account(0);
        for (int i = 0; i < 100; i++) single.deposit(1);
        Check.equal("one thread adds up", 100, single.balance());

        Bank.auditEntries.set(0);
        Bank.Account account = new Bank.Account(0);
        List<Thread> threads = new ArrayList<>();
        for (int t = 0; t < 8; t++) {
            threads.add(Thread.ofPlatform().start(() -> {
                for (int i = 0; i < 2_000; i++) account.deposit(1);
            }));
        }
        for (Thread t : threads) t.join();
        Check.equal("recordAuditEntry() still runs once per deposit", 16_000L, Bank.auditEntries.get());
        Check.equal("8 threads x 2,000 deposits of 1", 16_000, account.balance());
        Check.done();
    }
}
