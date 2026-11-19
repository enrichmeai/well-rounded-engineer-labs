import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Passwords {
    static final int ITERATIONS = 600_000;   // OWASP Password Storage Cheat Sheet, PBKDF2-HMAC-SHA256
    static final SecureRandom RANDOM = new SecureRandom();
    static final HexFormat HEX = HexFormat.of();

    static byte[] derive(String password, byte[] salt, int iterations) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } finally {
            spec.clearPassword();
        }
    }

    /** What we store: the algorithm, its cost and the salt travel with the hash, so they can be raised later. */
    static String hash(String password) throws Exception {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return "pbkdf2_sha256$" + ITERATIONS + "$" + HEX.formatHex(salt) + "$"
                + HEX.formatHex(derive(password, salt, ITERATIONS));
    }

    static boolean verify(String password, String stored) throws Exception {
        String[] part = stored.split("\\$");
        if (part.length != 4 || !part[0].equals("pbkdf2_sha256")) return false;
        byte[] attempt = derive(password, HEX.parseHex(part[2]), Integer.parseInt(part[1]));
        return MessageDigest.isEqual(attempt, HEX.parseHex(part[3]));   // constant time
    }
}
