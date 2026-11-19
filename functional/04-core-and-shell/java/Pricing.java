// Drill 04: the core and the shell. Make PricingTest pass.

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiConsumer;

public class Pricing {
    record Line(int qty, int pence) {}

    record Order(String email, List<Line> lines) {}

    /** Stands in for the real email service. Tests replace it. */
    static BiConsumer<String, Integer> sendReceipt = (email, total) -> {
        throw new IllegalStateException("tests must not send real email");
    };

    /** The order total in pence on the given day: 10% off at weekends. Pure: no clock, no email. */
    static int price(Order order, LocalDate day) {
        throw new UnsupportedOperationException("write me");
    }

    static int checkout(Order order) {
        int total = order.lines().stream().mapToInt(l -> l.qty() * l.pence()).sum();
        if (LocalDate.now().getDayOfWeek().getValue() >= 6) total = total * 90 / 100;
        sendReceipt.accept(order.email(), total);
        return total;
    }
}
