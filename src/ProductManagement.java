import java.util.List;

public interface ProductManagement {
    // В данном случае используется принцип Interface segregation так как
    // данный интерфейс выполняет свои задачи и не зависит от других
    void displayAllProducts();

    List<Product> filterByWord(String word);

    List<Product> filterByManufacturer(String manufacturer);

    List<Product> filterByPriceRange(double minPrice, double maxPrice);

    List<Product> getProducts();
}