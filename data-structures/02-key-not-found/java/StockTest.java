public class StockTest {
    public static void main(String[] args) {
        Stock s = new Stock();
        s.add("tee", 42, 5);
        s.add("tee", 42, 3);
        s.add("tee", 44, 1);
        Check.equal("tee 42 adds up", 8, s.inStock("tee", 42));
        Check.equal("tee 44", 1, s.inStock("tee", 44));
        Check.equal("hat 1 was never added", 0, s.inStock("hat", 1));
        Check.equal("one entry per (sku, size)", 2, s.counts.size());
        Check.equal("equal keys have equal hashes",
                new Stock.SkuKey("tee", 42).hashCode(), new Stock.SkuKey("tee", 42).hashCode());
        Check.done();
    }
}
