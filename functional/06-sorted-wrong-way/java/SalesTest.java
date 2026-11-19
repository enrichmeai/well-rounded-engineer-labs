import java.util.List;

public class SalesTest {
    public static void main(String[] args) {
        List<Sales.Sale> sales = List.of(new Sales.Sale("Leeds", 40, "paid"), new Sales.Sale("Bath", 15, "paid"),
                new Sales.Sale("Leeds", 90, "paid"), new Sales.Sale("Bath", 70, "cancelled"),
                new Sales.Sale("York", 55, "paid"), new Sales.Sale("Bath", 60, "paid"));
        Check.equal("stores A to Z, biggest sale first", List.of(new Sales.Row("Bath", 60), new Sales.Row("Bath", 15),
                new Sales.Row("Leeds", 90), new Sales.Row("Leeds", 40), new Sales.Row("York", 55)), Sales.dailyReport(sales));
        Check.done();
    }
}
