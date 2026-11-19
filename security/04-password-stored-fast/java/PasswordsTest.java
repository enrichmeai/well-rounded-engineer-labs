import java.util.HexFormat;

public class PasswordsTest {
    public static void main(String[] args) throws Exception {
        String stored = Passwords.hash("Summer2026!");
        Check.isTrue("the right password verifies", Passwords.verify("Summer2026!", stored));
        Check.isTrue("a wrong password does not", !Passwords.verify("summer2026!", stored));
        Check.isTrue("the same password twice is stored differently",
                !Passwords.hash("Summer2026!").equals(Passwords.hash("Summer2026!")));
        String[] part = Passwords.hash("x").split("\\$");
        Check.isTrue("the stored value has four parts: algorithm, iterations, salt, hash", part.length == 4);
        if (part.length == 4) {
            Check.equal("the algorithm", "pbkdf2_sha256", part[0]);
            Check.isTrue("at least 600,000 iterations", Integer.parseInt(part[1]) >= 600_000);
            Check.isTrue("a salt of at least 16 bytes", HexFormat.of().parseHex(part[2]).length >= 16);
        }
        Check.done();
    }
}
