import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Profile {
    static final Set<String> EDITABLE = Set.of("displayName", "email");   // what a customer may change, nothing else

    static class User {
        final int id;
        String email;
        String displayName;
        boolean isAdmin = false;
        int creditPence = 0;
        User(int id, String email, String displayName) { this.id = id; this.email = email; this.displayName = displayName; }
    }

    /** Apply the profile form's changes. Any field outside the allow-list refuses the whole request. */
    static void update(User user, Map<String, Object> changes) {
        Set<String> refused = new HashSet<>(changes.keySet());
        refused.removeAll(EDITABLE);
        if (!refused.isEmpty()) {
            throw new IllegalArgumentException("fields not editable: " + refused);
        }
        for (Map.Entry<String, Object> change : changes.entrySet()) {
            switch (change.getKey()) {
                case "email" -> user.email = (String) change.getValue();
                case "displayName" -> user.displayName = (String) change.getValue();
                default -> throw new IllegalStateException("not reached");
            }
        }
    }
}
