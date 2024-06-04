public class Product {
    // Данный класс является примером приципа Single Responsibility
    // так как он выполняет определенную задачу отличную от других классов
    private final int id;
    private final String name;
    private final String manufacturer;
    private final double price;

    public Product(int id, String name, String manufacturer, double price) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public int getProductId() {
        return id;
    }

    public String getProductName() {
        return name;
    }

    public String getProductManufacturer() {
        return manufacturer;
    }

    public double getProductPrice() {
        return price;
    }
}