import java.util.HashMap;
import java.util.Map;

public class RatingSystem {
    private final Map<Integer, Integer> ratings;

    public RatingSystem() {
        this.ratings = new HashMap<>();
    }

    public void rateProduct(int productId, int rating) {
        ratings.put(productId, rating);
    }

    public int getRating(int productId) {
        return ratings.getOrDefault(productId, 0);
    }
}
