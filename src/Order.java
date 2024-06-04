import java.util.List;

public class Order {
    private final int orderId;
    private final List<Product> items;
    private String status;
    private boolean isReturned;

    public Order(int orderId, List<Product> items) {
        this.orderId = orderId;
        this.items = items;
        this.status = "Заказ в процессе";
        this.isReturned = false;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Product> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void returnOrder() {
        this.isReturned = true;
        this.status = "Заказ возвращен";
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(Product::getProductPrice).sum();
    }

    public Order cloneOrder(int newOrderId) {
        return new Order(newOrderId, this.items);
    }
}
