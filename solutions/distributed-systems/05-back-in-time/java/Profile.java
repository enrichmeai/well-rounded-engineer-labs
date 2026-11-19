// Drill 05, solved: an update is applied only if it is newer than what the profile already has.

public class Profile {
    String email;
    int version;

    Profile(String email, int version) {
        this.email = email;
        this.version = version;
    }

    record EmailChanged(String email, int version) {}

    void apply(EmailChanged update) {
        if (update.version() <= version) return;     // old news, or a duplicate
        email = update.email();
        version = update.version();
    }
}
