// Drill 02, solved: one pass, two answers, with summary statistics.

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Stream;

public class Report {
    record Order(int total, String status) {}

    record Summary(long count, int revenue) {}

    static Stream<Integer> orderTotals(List<Order> orders) {
        return orders.stream().filter(o -> !o.status().equals("cancelled")).map(Order::total);
    }

    static Summary report(List<Order> orders) {
        IntSummaryStatistics s = orderTotals(orders).mapToInt(Integer::intValue).summaryStatistics();
        return new Summary(s.getCount(), (int) s.getSum());
    }
}
