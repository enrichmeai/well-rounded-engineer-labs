import java.util.ArrayList;
import java.util.List;

public class QueueTest {
    public static void main(String[] args) throws InterruptedException {
        Check.equal("oldest first", List.of(6, 2, 4), Jobs.processAll(List.of(3, 1, 2)));
        Check.equal("no jobs", List.of(), Jobs.processAll(List.of()));

        List<Integer> jobs = new ArrayList<>();
        for (int i = 0; i < 500_000; i++) jobs.add(i);
        List<Integer> result = Timed.within("500,000 jobs", 1.0, () -> Jobs.processAll(jobs));
        if (result != null) {
            Check.equal("first job", 0, result.get(0));
            Check.equal("last job", 999_998, result.get(result.size() - 1));
        }
        Check.done();
    }
}
