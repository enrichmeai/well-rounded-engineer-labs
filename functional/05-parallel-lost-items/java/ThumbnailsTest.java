import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ThumbnailsTest {
    public static void main(String[] args) {
        List<Integer> ids = IntStream.range(0, 100_000).boxed().toList();
        List<String> expected = new ArrayList<>();
        for (int i : ids) expected.add("thumb-" + i);
        boolean allRight = true;
        String problem = "";
        for (int run = 0; run < 5 && allRight; run++) {
            try {
                List<String> got = Thumbnails.makeThumbnails(ids);
                if (got.size() != expected.size()) problem = "run " + run + ": " + got.size() + " of 100,000 thumbnails";
                else if (!got.equals(expected)) problem = "run " + run + ": all there, wrong order";
                allRight = problem.isEmpty();
            } catch (RuntimeException e) {
                allRight = false;
                problem = "run " + run + ": " + e;
            }
        }
        Check.isTrue("every thumbnail, in input order, five runs out of five " + problem, allRight);
        Check.done();
    }
}
