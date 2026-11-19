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

    /** One place that fetches an order AND checks it belongs to the caller. */
    private static Order orderFor(Map<Integer, Order> store, String user, int orderId) {
        Order order = store.get(orderId);
        if (order == null || !order.owner.equals(user)) {
            throw new NotFoundException("no order " + orderId);   // the same answer: do not reveal that it exists
        }
        return order;
    }

    static Order get(Map<Integer, Order> store, String user, int orderId) {
        return orderFor(store, user, orderId);
    }

    static void cancel(Map<Integer, Order> store, String user, int orderId) {
        orderFor(store, user, orderId).status = "cancelled";
    }
}
