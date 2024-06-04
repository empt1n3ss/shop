import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int orderIdCounter = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // В данном случае применяется DRY, для создания продуктов и помещения их в каталог
        List<Product> products = Arrays.asList(
                new Product(1, "Laptop D1", "Dell", 100000),
                new Product(2, "Laptop A6", "Apple", 800000),
                new Product(3, "Laptop S26", "Samsung", 160000),
                new Product(4, "Tablet L6", "Dell", 16000),
                new Product(5, "Tablet AP5", "Apple", 90000),
                new Product(6, "Tablet ST9", "Samsung", 85000),
                new Product(7, "Phone U7", "Xiaomi", 60000),
                new Product(8, "Phone IP14", "Apple", 99000),
                new Product(9, "Phone S67", "Samsung", 90000)
        );

        // В данном случае применяется принцип Dependency Inversion для внедрения зависимостей через интерфейсы
        ProductManagement catalog = new ProductCatalog(products);
        BasketManagement basket = new Basket();
        OrderManagement orderTracking = new OrderTracking();
        RatingSystem ratingSystem = new RatingSystem();

        while (true) {
            System.out.println("Выберите действие.");
            System.out.println("1. Вывод доступных для покупки товаров.");
            System.out.println("2. Фильтрация товаров по ключевым словам.");
            System.out.println("3. Фильтрация товаров по ценам.");
            System.out.println("4. Фильтрация товаров по производителям.");
            System.out.println("5. Составить продуктовую корзину.");
            System.out.println("6. Трекинг заказа в системе доставки.");
            System.out.println("7. Возврат заказа.");
            System.out.println("8. Повтороение заказа.");
            System.out.println("9. Оценить товар.");
            System.out.println("10. Показать рейтинг товаров.");
            System.out.println("11. Показать рекомендованные товары.");
            System.out.println("12. Выйти из программы.");
            System.out.print("Ввод: ");
            try {
                int userAnswer = Integer.parseInt(sc.nextLine());
                switch (userAnswer) {
                    case 1:
                        catalog.displayAllProducts();
                        break;
                    case 2:
                        System.out.print("Введите ключенвое слово: ");
                        String keyword = sc.nextLine();
                        List<Product> filteredProducts = catalog.filterByWord(keyword);
                        DisplayProducts.displayProducts(filteredProducts);
                        break;
                    case 3:
                        System.out.print("Введите минимальную цену: ");
                        double minPrice = Double.parseDouble(sc.nextLine());
                        System.out.print("Введите максимальную цену: ");
                        double maxPrice = Double.parseDouble(sc.nextLine());
                        List<Product> filteredProductsByPrice = catalog.filterByPriceRange(minPrice, maxPrice);
                        DisplayProducts.displayProducts(filteredProductsByPrice);
                        break;
                    case 4:
                        System.out.print("Введите производителя: ");
                        String manufacturer = sc.nextLine();
                        List<Product> filteredProductsByManufacturer = catalog.filterByManufacturer(manufacturer);
                        DisplayProducts.displayProducts(filteredProductsByManufacturer);
                        break;
                    case 5:
                        basket.interactWithUser(catalog, sc, orderIdCounter);
                        System.out.println("Корзина пользователя:");
                        DisplayProducts.displayProducts(basket.getItems());
                        if (!basket.getItems().isEmpty()) {
                            System.out.println("Хотите ли вы сделать заказ (да/нет)");
                            System.out.print("Ввод: ");
                            String orderAnswer = sc.nextLine();
                            if (orderAnswer.equalsIgnoreCase("да")) {
                                List<Product> itemsInBasket = basket.getItems().stream()
                                        .map(product -> new Product(product.getProductId(),
                                                product.getProductName(),
                                                product.getProductManufacturer(),
                                                product.getProductPrice()))
                                        .toList();
                                Order order = new Order(orderIdCounter, itemsInBasket);
                                orderTracking.addOrder(order);
                                System.out.println("Вы заказали товар.");
                                System.out.println("Id заказа: " + order.getOrderId());
                                orderIdCounter++;
                                basket.clean();
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                        break;
                    case 6:
                        if (orderTracking.hasOrders()) {
                            orderTracking.displayAllOrders();
                            System.out.print("Введите ID заказа для трекинга: ");
                            int orderIdForTracking = Integer.parseInt(sc.nextLine());
                            Order trackedOrder = orderTracking.getOrder(orderIdForTracking);
                            if (trackedOrder != null) {
                                System.out.println("Статус заказа: " + trackedOrder.getStatus());
                            } else {
                                System.out.println("Заказ не найден.");
                            }
                        } else {
                            System.out.println("Нет текущих заказов.");
                        }
                        break;
                    case 7:
                        if (orderTracking.hasOrders()) {
                            orderTracking.displayAllOrders();
                            System.out.print("Введите ID заказа для возврата: ");
                            int orderIdForReturn = Integer.parseInt(sc.nextLine());
                            orderTracking.returnOrder(orderIdForReturn);
                            break;
                        } else {
                            System.out.println("Нет текущих заказов.");
                        }
                        break;
                    case 8:
                        if (orderTracking.hasOrders()) {
                            orderTracking.displayAllOrders();
                            System.out.print("Введите ID заказа для повторения: ");
                            int originalOrderId = Integer.parseInt(sc.nextLine());
                            Order repeatedOrder = orderTracking.repeatOrder(originalOrderId, orderIdCounter++);
                            if (repeatedOrder != null) {
                                System.out.println("Заказ повторен. Новый ID заказа: " + repeatedOrder.getOrderId());
                            } else {
                                System.out.println("Не удалось повторить заказ.");
                            }
                        } else {
                            System.out.println("Нет текущих заказов.");
                        }
                        break;
                    case 9:
                        catalog.displayAllProducts();
                        System.out.print("Введите ID товара для оценки: ");
                        int productId = Integer.parseInt(sc.nextLine());
                        //В данном случае используется правило магических чисел, для указания рейтинга товара через переменную
                        System.out.print("Введите оценку (1-5): ");
                        int rating = Integer.parseInt(sc.nextLine());
                        ratingSystem.rateProduct(productId, rating);
                        break;
                    case 10:
                        System.out.println("Рейтинги товаров:");
                        catalog.getProducts().forEach(product ->
                                System.out.println("ID: " + product.getProductId() +
                                        ", Название: " + product.getProductName() +
                                        ", Рейтинг: " + ratingSystem.getRating(product.getProductId())));
                        break;
                    case 11:
                        System.out.println("Список рекомендованных товаров основанный на вашей оценке:");
                        RecommendationSystem recommendationSystem = new RecommendationSystem(catalog, ratingSystem);
                        List<Product> recommendedProducts = recommendationSystem.recommendProducts();
                        DisplayProducts.displayProducts(recommendedProducts);
                        break;
                    case 12:
                        System.out.println("Выход из программы.");
                        sc.close();
                        return;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите снова.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
            }
        }
    }
}
