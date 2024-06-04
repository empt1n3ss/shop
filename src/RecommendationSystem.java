import java.util.List;
import java.util.stream.Collectors;

public class RecommendationSystem {
    private final ProductManagement catalog;
    private final RatingSystem ratingSystem;

    public RecommendationSystem(ProductManagement catalog, RatingSystem ratingSystem) {
        this.catalog = catalog;
        this.ratingSystem = ratingSystem;
    }

    public List<Product> recommendProducts() {
        return catalog.getProducts().stream()
                .filter(product -> ratingSystem.getRating(product.getProductId()) >= 4)
                .collect(Collectors.toList());
    }
}
