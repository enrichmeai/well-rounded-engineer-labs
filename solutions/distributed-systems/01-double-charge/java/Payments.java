// Drill 01, solved: the service remembers each idempotency key and its receipt.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Payments {
    final List<Integer> charges = new ArrayList<>();
    final Map<String, String> receipts = new HashMap<>();   // idempotency key -> receipt

    String charge(String idempotencyKey, String order, int amount) {
        String seen = receipts.get(idempotencyKey);
        if (seen != null) return seen;                       // a retry: no new charge
        charges.add(amount);
        String receipt = "receipt-" + charges.size();
        receipts.put(idempotencyKey, receipt);
        return receipt;
    }

    int totalCharged() {
        return charges.stream().mapToInt(Integer::intValue).sum();
    }
}
