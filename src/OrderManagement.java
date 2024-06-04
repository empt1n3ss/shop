public interface OrderManagement {
    void addOrder(Order order);

    Order getOrder(int orderId);

    void updateOrderStatus(int orderId, String status);

    void returnOrder(int orderId);

    Order repeatOrder(int originalOrderId, int orderIdCounter);

    boolean hasOrders();

    void displayAllOrders();
}