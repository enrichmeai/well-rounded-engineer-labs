// Drill 06: the report sorted the wrong way. Make SalesTest pass.

import java.util.Comparator;
import java.util.List;

public class Sales {
    record Sale(String store, int total, String status) {}

    record Row(String store, int total) {}

    /** Leave out cancelled sales; one row per sale; stores A to Z, biggest sale first within a store. */
    static List<Row> dailyReport(List<Sale> sales) {
        return sales.stream()
                .filter(s -> !s.status().equals("cancelled"))
                .map(s -> new Row(s.store(), s.total()))
                .sorted(Comparator.comparing(Row::store))                              // by store
                .sorted(Comparator.comparing(Row::total, Comparator.reverseOrder()))   // then by total
                .toList();
    }
}
