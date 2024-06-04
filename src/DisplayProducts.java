import java.util.List;

public class DisplayProducts {
    public static void displayProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("Список пуст.");
        } else {
            products.forEach(product -> System.out.println("ID:" + product.getProductId() + ", Название: " +
                    product.getProductName() + ", Производитель: " + product.getProductManufacturer() + ", Цена: " +
                    product.getProductPrice()));
        }
    }
}