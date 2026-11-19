import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

public class Passwords {
    /** What we store in the users table. */
    static String hash(String password) throws Exception {
        byte[] digest = MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8));
        return HexFormat.of().formatHex(digest);
    }

    static boolean verify(String password, String stored) throws Exception {
        return hash(password).equals(stored);
    }
}
