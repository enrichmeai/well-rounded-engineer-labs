// Drill 01: the double charge. Make PaymentsTest pass.

import java.util.ArrayList;
import java.util.List;

public class Payments {
    final List<Integer> charges = new ArrayList<>();        // what the card was actually charged

    /** Charge the card and return a receipt id. The client sends the same key when it retries. */
    String charge(String idempotencyKey, String order, int amount) {
        charges.add(amount);
        return "receipt-" + charges.size();
    }

    int totalCharged() {
        return charges.stream().mapToInt(Integer::intValue).sum();
    }
}
