// Drill 05: the update that went back in time. Make ProfileTest pass.

public class Profile {
    String email;
    int version;

    Profile(String email, int version) {
        this.email = email;
        this.version = version;
    }

    record EmailChanged(String email, int version) {}

    /** Apply one update as it arrives from the event stream. */
    void apply(EmailChanged update) {
        email = update.email();
        version = update.version();
    }
}
