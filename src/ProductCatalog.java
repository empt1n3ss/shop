import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalog implements ProductManagement {
    // Данный класс является примером приципа Open/Closed
    // так как мы имеем возможность добавлять продукты в каталог без изменения существующего кода
    private final List<Product> products;

    public ProductCatalog(List<Product> products) {
        this.products = products;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void displayAllProducts() {
        products.forEach(product -> System.out.println("ID:" + product.getProductId() + ", Название: " +
                product.getProductName() + ", Производитель: " + product.getProductManufacturer() + ", Цена: " +
                product.getProductPrice()));
    }

    @Override
    public List<Product> filterByWord(String word) {
        return products.stream()
                .filter(product -> product.getProductName().contains(word))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> filterByManufacturer(String manufacturer) {
        return products.stream()
                .filter(product -> product.getProductManufacturer().contains(manufacturer))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> filterByPriceRange(double minPrice, double maxPrice) {
        return products.stream()
                .filter(product -> product.getProductPrice() >= minPrice && product.getProductPrice() <= maxPrice)
                .collect(Collectors.toList());
    }
}
