public class TransfersTest {
    public static void main(String[] args) throws InterruptedException {
        Transfers.Account a = new Transfers.Account(1, 100), b = new Transfers.Account(2, 100);
        Transfers.transfer(a, b, 30);
        Check.equal("one transfer", "70/130", a.balance + "/" + b.balance);

        Transfers.Account x = new Transfers.Account(1, 100), y = new Transfers.Account(2, 100);
        Thread there = new Thread(() -> { for (int i = 0; i < 200; i++) Transfers.transfer(x, y, 1); });
        Thread back = new Thread(() -> { for (int i = 0; i < 200; i++) Transfers.transfer(y, x, 1); });
        there.setDaemon(true);
        back.setDaemon(true);
        there.start();
        back.start();
        there.join(5_000);
        back.join(1_000);
        boolean finished = !there.isAlive() && !back.isAlive();
        Check.isTrue("both directions finish (no deadlock after 5 seconds)", finished);
        if (finished) {                      // only read the balances once nobody holds their locks
            Check.equal("money is neither created nor lost", 200, x.balance + y.balance);
        }
        Check.done();
    }
}
