import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RequestLogTest {
    public static void main(String[] args) {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("User-Agent", "shop-app/5.2");
        headers.put("Content-Type", "application/json");
        headers.put("X-Request-Id", "req-7781");
        headers.put("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.c2Vzc2lvbg.sig");
        headers.put("X-Api-Key", "sk_live_51Hx9QaZ");
        headers.put("Cookie", "session=8f14e45fceea167a");
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("order_id", 1043);
        body.put("currency", "GBP");
        body.put("items", 3);
        body.put("card_number", "4111111111111111");
        body.put("card_cvc", "737");
        body.put("password", "Summer2026!");
        body.put("otp", "492817");

        List<String> lines = new ArrayList<>();
        RequestLog.log(headers, body, lines::add);
        String out = String.join("\n", lines);

        for (String secret : List.of("eyJhbGciOiJIUzI1NiJ9", "sk_live_51Hx9QaZ", "8f14e45fceea167a", "4111111111111111",
                "737", "Summer2026!", "492817")) {
            Check.isTrue("not in the log: " + secret, !out.contains(secret));
        }
        for (String useful : List.of("shop-app/5.2", "req-7781", "1043", "GBP", "card_number", "Authorization", "[redacted]")) {
            Check.isTrue("still in the log: " + useful, out.contains(useful));
        }
        Check.done();
    }
}
