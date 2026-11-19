// Drill 06, solved: one sort with a comparator that has two keys.

import java.util.Comparator;
import java.util.List;

public class Sales {
    record Sale(String store, int total, String status) {}

    record Row(String store, int total) {}

    static List<Row> dailyReport(List<Sale> sales) {
        return sales.stream()
                .filter(s -> !s.status().equals("cancelled"))
                .map(s -> new Row(s.store(), s.total()))
                .sorted(Comparator.comparing(Row::store)
                        .thenComparing(Row::total, Comparator.reverseOrder()))
                .toList();
    }
}
