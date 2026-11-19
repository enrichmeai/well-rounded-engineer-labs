import java.util.LinkedHashMap;
import java.util.Map;

public class ProfileTest {
    static boolean refused(Profile.User user, Map<String, Object> changes) {
        try {
            Profile.update(user, changes);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static void main(String[] args) {
        Profile.User ana = new Profile.User(7, "ana@example.com", "Ana");
        Profile.update(ana, Map.of("displayName", "Ana P", "email", "ana.p@example.com"));
        Check.equal("a customer can change their name", "Ana P", ana.displayName);
        Check.equal("a customer can change their email", "ana.p@example.com", ana.email);

        Profile.User user = new Profile.User(7, "ana@example.com", "Ana");
        Check.isTrue("becoming an admin is refused", refused(user, Map.of("isAdmin", true)));
        Check.isTrue("and the user is not an admin", !user.isAdmin);

        Profile.User other = new Profile.User(7, "ana@example.com", "Ana");
        Map<String, Object> mixed = new LinkedHashMap<>();
        mixed.put("displayName", "Ana P");
        mixed.put("creditPence", 100_000);
        Check.isTrue("adding credit is refused", refused(other, mixed));
        Check.equal("a refused request changes nothing: name", "Ana", other.displayName);
        Check.equal("a refused request changes nothing: credit", 0, other.creditPence);
        Check.done();
    }
}
