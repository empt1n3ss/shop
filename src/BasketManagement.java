import java.util.List;
import java.util.Scanner;

public interface BasketManagement {
    void addProduct(Product product);

    void deleteProduct(Product product);

    List<Product> getItems();

    double getTotalPrice();

    void clean();

    void interactWithUser(ProductManagement catalog, Scanner sc, int orderIdCounter);
}