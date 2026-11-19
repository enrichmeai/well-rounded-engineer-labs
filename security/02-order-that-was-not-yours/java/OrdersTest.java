import java.util.HashMap;
import java.util.Map;

public class OrdersTest {
    static String failure(Runnable r) {
        try {
            r.run();
            return null;
        } catch (Orders.NotFoundException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        Map<Integer, Orders.Order> store = new HashMap<>();
        store.put(1043, new Orders.Order("ana", 2599));
        store.put(1044, new Orders.Order("ben", 4800));

        Check.equal("ana reads her order", 2599, Orders.get(store, "ana", 1043).total);
        Orders.cancel(store, "ana", 1043);
        Check.equal("ana cancels her order", "cancelled", store.get(1043).status);

        String notYours = failure(() -> Orders.get(store, "ana", 1044));
        Check.isTrue("ana cannot read ben's order", notYours != null);
        Check.isTrue("ana cannot cancel ben's order", failure(() -> Orders.cancel(store, "ana", 1044)) != null);
        Check.equal("ben's order is still paid", "paid", store.get(1044).status);

        String missing = failure(() -> Orders.get(store, "ana", 1045));
        Check.equal("not yours looks the same as does not exist",
                String.valueOf(missing).replace("1045", "N"), String.valueOf(notYours).replace("1044", "N"));
        Check.done();
    }
}
