import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Random;

public class ResetTokens {
    static final Duration LIFETIME = Duration.ofMinutes(15);
    static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    record Entry(String user, Instant expires) {}

    /** Create a reset token for user, to send in the reset email. */
    static String issue(Map<String, Entry> store, String user, Instant now) {
        Random rng = new Random(now.toEpochMilli());
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 12; i++) token.append(ALPHABET.charAt(rng.nextInt(ALPHABET.length())));
        store.put(token.toString(), new Entry(user, now.plus(LIFETIME)));
        return token.toString();
    }

    /** The user this token resets, or null. */
    static String redeem(Map<String, Entry> store, String token, Instant now) {
        Entry entry = store.get(token);
        if (entry == null || now.isAfter(entry.expires())) return null;
        return entry.user();
    }
}
