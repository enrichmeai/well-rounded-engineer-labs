public class FencingTest {
    public static void main(String[] args) {
        Fencing.LockService locks = new Fencing.LockService();
        Fencing.Storage storage = new Fencing.Storage();
        long a = locks.acquire();                    // worker A gets the lease, then freezes
        long b = locks.acquire();                    // A's lease expires; worker B gets it
        Check.isTrue("B's write is accepted", storage.write("report by B", b));
        Check.isTrue("A's late write is refused", !storage.write("stale report by A", a));
        Check.equal("B's report stays", "report by B", storage.report);

        Fencing.LockService l2 = new Fencing.LockService();
        Fencing.Storage s2 = new Fencing.Storage();
        long t = l2.acquire();
        s2.write("draft", t);
        Check.isTrue("the holder can write again", s2.write("final", t));
        Check.equal("its last write stays", "final", s2.report);
        Check.done();
    }
}
