import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PricingTest {
    public static void main(String[] args) {
        Pricing.Order order = new Pricing.Order("ana@example.com", List.of(new Pricing.Line(2, 1250), new Pricing.Line(1, 500)));
        Pricing.sendReceipt = (e, t) -> { throw new AssertionError("price() sent an email"); };
        try {
            Check.equal("a Wednesday", 3000, Pricing.price(order, LocalDate.of(2026, 11, 18)));
            Check.equal("a Saturday", 2700, Pricing.price(order, LocalDate.of(2026, 11, 21)));
        } catch (RuntimeException | AssertionError e) {
            Check.isTrue("price works without a clock or email: " + e.getMessage(), false);
        }
        List<String> sent = new ArrayList<>();
        Pricing.sendReceipt = (e, t) -> sent.add(e + " " + t);
        try {
            int total = Pricing.checkout(order);
            Check.equal("one receipt with the total", List.of("ana@example.com " + total), sent);
            Check.equal("checkout uses price", Pricing.price(order, LocalDate.now()), total);
        } catch (RuntimeException e) {
            Check.isTrue("checkout works: " + e.getMessage(), false);
        }
        Check.done();
    }
}
