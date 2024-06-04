import java.util.HashMap;
import java.util.Map;

public class OrderTracking implements OrderManagement {
    private final Map<Integer, Order> orderMap;

    public OrderTracking() {
        this.orderMap = new HashMap<>();
    }

    @Override
    public void addOrder(Order order) {
        orderMap.put(order.getOrderId(), order);
    }

    @Override
    public Order getOrder(int orderId) {
        return orderMap.get(orderId);
    }

    @Override
    public void updateOrderStatus(int orderId, String status) {
        Order order = orderMap.get(orderId);
        if (order != null) {
            order.setStatus(status);
        }
    }

    @Override
    public void returnOrder(int orderId) {
        Order order = orderMap.get(orderId);
        if (order != null) {
            order.returnOrder();
            System.out.println("Заказ возвращен");
        }
    }

    // В данном случае используется принцип Liskov Substitution так как экземпляры класса Order
    // могут содержать объекты класса Product
    @Override
    public Order repeatOrder(int originalOrderId, int orderIdCounter) {
        Order originalOrder = orderMap.get(originalOrderId);
        if (originalOrder != null) {
            Order newOrder = originalOrder.cloneOrder(orderIdCounter);
            addOrder(newOrder);
            return newOrder;
        }
        return null;
    }

    @Override
    public boolean hasOrders() {
        return !orderMap.isEmpty();
    }

    @Override
    public void displayAllOrders() {
        System.out.println("Текущие заказы:");
        for (Order order : orderMap.values()) {
            System.out.println("ID заказа: " + order.getOrderId());
            System.out.println("Товары:");
            for (Product product : order.getItems()) {
                System.out.println("- " + product.getProductName() + ", Производитель: " +
                        product.getProductManufacturer() + ", Цена: " + product.getProductPrice());
            }
            System.out.println("Сумма заказа:" + order.getTotalPrice());
            System.out.println();
        }
    }
}
