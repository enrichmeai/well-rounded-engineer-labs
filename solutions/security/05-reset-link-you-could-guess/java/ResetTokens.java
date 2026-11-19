import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;

public class ResetTokens {
    static final Duration LIFETIME = Duration.ofMinutes(15);
    static final SecureRandom RANDOM = new SecureRandom();

    record Entry(String user, Instant expires) {}

    /** Create a reset token for user: 32 random bytes from a secure generator. */
    static String issue(Map<String, Entry> store, String user, Instant now) {
        byte[] bytes = new byte[32];
        RANDOM.nextBytes(bytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        store.put(token, new Entry(user, now.plus(LIFETIME)));
        return token;
    }

    /** The user this token resets, or null. A token is removed when it is used, so it works once. */
    static String redeem(Map<String, Entry> store, String token, Instant now) {
        Entry entry = store.remove(token);
        if (entry == null || now.isAfter(entry.expires())) return null;
        return entry.user();
    }
}
