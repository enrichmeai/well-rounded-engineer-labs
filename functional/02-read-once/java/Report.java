// Drill 02: the pipeline you can only read once. Make ReportTest pass.

import java.util.List;
import java.util.stream.Stream;

public class Report {
    record Order(int total, String status) {}

    record Summary(long count, int revenue) {}

    /** The totals of the orders that were not cancelled, produced lazily. */
    static Stream<Integer> orderTotals(List<Order> orders) {
        return orders.stream().filter(o -> !o.status().equals("cancelled")).map(Order::total);
    }

    static Summary report(List<Order> orders) {
        Stream<Integer> totals = orderTotals(orders);
        long count = totals.count();
        int revenue = totals.mapToInt(Integer::intValue).sum();
        return new Summary(count, revenue);
    }
}
