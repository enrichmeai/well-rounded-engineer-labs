import java.util.List;

public class CacheTest {
    public static void main(String[] args) {
        Cache<String, Integer> c = new Cache<>(2);
        c.put("home", 1);
        c.put("sale", 2);
        Check.equal("read home", 1, c.get("home"));        // "home" is now the most recently used
        c.put("about", 3);                                 // full: evict the least recently used, "sale"
        Check.equal("evicts the least recently used", List.of("sale"), c.evicted);
        Check.equal("home survives", 1, c.get("home"));

        Cache<String, Integer> u = new Cache<>(2);
        u.put("a", 1);
        u.put("b", 2);
        u.put("a", 10);                                    // an update is a use too
        u.put("c", 3);
        Check.equal("an update counts as use", List.of("b"), u.evicted);

        Cache<Integer, Integer> small = new Cache<>(3);
        for (int i = 0; i < 10; i++) small.put(i, i);
        Check.equal("never more than capacity", 3, small.size());
        Check.done();
    }
}
