import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ResetTokensTest {
    static int randomness(String token) {
        try {
            return Base64.getUrlDecoder().decode(token).length;
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Instant now = Instant.parse("2026-11-19T09:30:00Z");
        Map<String, ResetTokens.Entry> store = new HashMap<>();
        Check.isTrue("two tokens at the same moment differ",
                !ResetTokens.issue(store, "ana", now).equals(ResetTokens.issue(store, "ben", now)));
        Check.isTrue("a token carries at least 32 bytes", randomness(ResetTokens.issue(new HashMap<>(), "ana", now)) >= 32);

        Map<String, ResetTokens.Entry> once = new HashMap<>();
        String token = ResetTokens.issue(once, "ana", now);
        Check.equal("a token works", "ana", ResetTokens.redeem(once, token, now.plus(Duration.ofMinutes(1))));
        Check.equal("a token works only once", null, ResetTokens.redeem(once, token, now.plus(Duration.ofMinutes(2))));

        Map<String, ResetTokens.Entry> late = new HashMap<>();
        String old = ResetTokens.issue(late, "ana", now);
        Check.equal("a token expires after 15 minutes", null, ResetTokens.redeem(late, old, now.plus(Duration.ofMinutes(16))));
        Check.done();
    }
}
