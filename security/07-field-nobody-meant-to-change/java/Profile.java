import java.util.Map;

public class Profile {
    static class User {
        final int id;
        String email;
        String displayName;
        boolean isAdmin = false;
        int creditPence = 0;
        User(int id, String email, String displayName) { this.id = id; this.email = email; this.displayName = displayName; }
    }

    /** Apply the profile form's changes to the user: every field the request names, as a data binder would. */
    static void update(User user, Map<String, Object> changes) {
        for (Map.Entry<String, Object> change : changes.entrySet()) {
            switch (change.getKey()) {
                case "email" -> user.email = (String) change.getValue();
                case "displayName" -> user.displayName = (String) change.getValue();
                case "isAdmin" -> user.isAdmin = (Boolean) change.getValue();
                case "creditPence" -> user.creditPence = (Integer) change.getValue();
                default -> throw new IllegalArgumentException("unknown field " + change.getKey());
            }
        }
    }
}
