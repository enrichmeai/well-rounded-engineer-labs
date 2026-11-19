public class DuplicatesTest {
    public static void main(String[] args) throws InterruptedException {
        Check.equal("empty", false, Duplicates.hasDuplicate(new int[] {}));
        Check.equal("one id", false, Duplicates.hasDuplicate(new int[] {7}));
        Check.equal("3 1 4 1 5", true, Duplicates.hasDuplicate(new int[] {3, 1, 4, 1, 5}));
        Check.equal("1 2 3", false, Duplicates.hasDuplicate(new int[] {1, 2, 3}));

        int[] ids = new int[300_000];               // no duplicates: the slowest case for any check
        for (int i = 0; i < ids.length; i++) ids[i] = i;
        Boolean result = Timed.within("300,000 ids", 1.0, () -> Duplicates.hasDuplicate(ids));
        if (result != null) Check.equal("300,000 distinct ids", false, result);
        Check.done();
    }
}
