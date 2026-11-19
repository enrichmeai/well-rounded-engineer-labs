// Drill 04, solved: pricing is a pure function; the clock and the email stay in checkout, the thin shell.

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiConsumer;

public class Pricing {
    record Line(int qty, int pence) {}

    record Order(String email, List<Line> lines) {}

    static BiConsumer<String, Integer> sendReceipt = (email, total) -> {
        throw new IllegalStateException("tests must not send real email");
    };

    static int price(Order order, LocalDate day) {
        int total = order.lines().stream().mapToInt(l -> l.qty() * l.pence()).sum();
        if (day.getDayOfWeek().getValue() >= 6) total = total * 90 / 100;     // Saturday or Sunday
        return total;
    }

    static int checkout(Order order) {
        int total = price(order, LocalDate.now());    // the shell reads the clock...
        sendReceipt.accept(order.email(), total);     // ...and talks to the world
        return total;
    }
}
