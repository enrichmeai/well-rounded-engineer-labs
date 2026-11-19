public class DepthTest {
    static Categories.Category chain(int n) {
        Categories.Category node = new Categories.Category("top", null);
        for (int i = 0; i < n; i++) node = new Categories.Category("level-" + (i + 1), node);
        return node;
    }

    public static void main(String[] args) {
        Check.equal("top", 0, Categories.depth(chain(0)));
        Check.equal("one level", 1, Categories.depth(chain(1)));
        Check.equal("five levels", 5, Categories.depth(chain(5)));
        try {
            Check.equal("100,000 levels", 100_000, Categories.depth(chain(100_000)));
        } catch (StackOverflowError e) {
            Check.isTrue("100,000 levels without StackOverflowError", false);
        }
        Check.done();
    }
}
