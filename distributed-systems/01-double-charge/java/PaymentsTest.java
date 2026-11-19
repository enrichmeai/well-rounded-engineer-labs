import java.util.HashSet;
import java.util.Set;

public class PaymentsTest {
    public static void main(String[] args) {
        Payments s = new Payments();
        String first = s.charge("key-order-7", "order-7", 100);
        String second = s.charge("key-order-7", "order-7", 100);    // the reply was lost; the app retries
        Check.equal("a retry with the same key charges once", 100, s.totalCharged());
        Check.equal("a retry gets the original receipt back", first, second);

        Payments t = new Payments();
        t.charge("key-order-7", "order-7", 100);
        t.charge("key-order-8", "order-8", 40);
        Check.equal("a new key is a new charge", 140, t.totalCharged());

        Payments u = new Payments();
        Set<String> receipts = new HashSet<>();
        for (int i = 0; i < 5; i++) receipts.add(u.charge("key-order-9", "order-9", 25));
        Check.equal("five retries, one charge", 25, u.totalCharged());
        Check.equal("five retries, one receipt", 1, receipts.size());
        Check.done();
    }
}
