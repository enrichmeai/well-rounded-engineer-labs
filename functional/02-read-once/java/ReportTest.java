import java.util.List;

public class ReportTest {
    public static void main(String[] args) {
        List<Report.Order> orders = List.of(new Report.Order(30, "paid"), new Report.Order(12, "cancelled"),
                new Report.Order(25, "paid"), new Report.Order(8, "paid"));
        try {
            Check.equal("count and revenue", new Report.Summary(3, 63), Report.report(orders));
            Check.equal("nothing to report", new Report.Summary(0, 0), Report.report(List.of()));
        } catch (IllegalStateException e) {
            Check.isTrue("report ran without " + e.getMessage(), false);
        }
        Check.done();
    }
}
