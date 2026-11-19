import java.util.ArrayList;
import java.util.List;

public class QuorumTest {
    static List<Quorum.Replica> fresh() {
        List<Quorum.Replica> r = new ArrayList<>();
        for (int i = 0; i < Quorum.N; i++) r.add(new Quorum.Replica("12 Old Road", 1));
        return r;
    }

    public static void main(String[] args) {
        List<Quorum.Replica> replicas = fresh();
        Check.isTrue("the write reaches 2 of 3 and succeeds", Quorum.write(replicas, List.of(0, 1), "3 New Street", 2));
        boolean allNew = true;
        String bad = "";
        for (int a = 0; a < Quorum.N; a++) {                 // every ordered pair of replicas, and every single one
            for (int b = 0; b < Quorum.N; b++) {
                if (Quorum.R == 2 && a == b) continue;
                List<Quorum.Replica> asked = Quorum.R == 1 ? List.of(replicas.get(a)) : List.of(replicas.get(a), replicas.get(b));
                String got = Quorum.read(asked);
                if (!got.equals("3 New Street")) {
                    allNew = false;
                    bad = "reading " + a + (Quorum.R == 2 ? "," + b : "") + " returned " + got;
                }
            }
        }
        Check.isTrue("every read sees the newest value " + bad, allNew);
        Check.isTrue("W + R > N", Quorum.W + Quorum.R > Quorum.N);
        Check.isTrue("a write that reaches one replica is refused", !Quorum.write(fresh(), List.of(0), "3 New Street", 2));
        Check.done();
    }
}
