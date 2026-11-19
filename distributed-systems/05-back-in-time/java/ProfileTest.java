import java.util.ArrayList;
import java.util.List;

public class ProfileTest {
    static final List<Profile.EmailChanged> UPDATES = List.of(new Profile.EmailChanged("ana@old.example", 1),
            new Profile.EmailChanged("ana@typo.exmaple", 2), new Profile.EmailChanged("ana@new.example", 3));

    static void permute(List<Profile.EmailChanged> rest, List<Profile.EmailChanged> sofar, List<List<Profile.EmailChanged>> out) {
        if (rest.isEmpty()) {
            out.add(sofar);
            return;
        }
        for (Profile.EmailChanged u : rest) {
            List<Profile.EmailChanged> r = new ArrayList<>(rest);
            r.remove(u);
            List<Profile.EmailChanged> s = new ArrayList<>(sofar);
            s.add(u);
            permute(r, s, out);
        }
    }

    public static void main(String[] args) {
        List<List<Profile.EmailChanged>> orders = new ArrayList<>();
        permute(UPDATES, List.of(), orders);
        for (List<Profile.EmailChanged> order : orders) {
            Profile p = new Profile("ana@first.example", 0);
            for (Profile.EmailChanged u : order) p.apply(u);
            Check.equal("arrival order " + order.stream().map(Profile.EmailChanged::version).toList(),
                    "ana@new.example v3", p.email + " v" + p.version);
        }
        Profile p = new Profile("ana@first.example", 0);
        for (Profile.EmailChanged u : List.of(UPDATES.get(0), UPDATES.get(2), UPDATES.get(1), UPDATES.get(0))) p.apply(u);
        Check.equal("duplicates change nothing", "ana@new.example v3", p.email + " v" + p.version);
        Check.done();
    }
}
