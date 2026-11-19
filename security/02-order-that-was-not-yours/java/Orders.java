import java.util.Map;

public class Orders {
    static class NotFoundException extends RuntimeException {
        NotFoundException(String message) { super(message); }
    }

    static class Order {
        final String owner;
        final int total;
        String status = "paid";
        Order(String owner, int total) { this.owner = owner; this.total = total; }
    }

    /** The order with this ID, for the signed-in user. */
    static Order get(Map<Integer, Order> store, String user, int orderId) {
        Order order = store.get(orderId);
        if (order == null) throw new NotFoundException("no order " + orderId);
        return order;
    }

    static void cancel(Map<Integer, Order> store, String user, int orderId) {
        Order order = store.get(orderId);
        if (order == null) throw new NotFoundException("no order " + orderId);
        order.status = "cancelled";
    }
}
