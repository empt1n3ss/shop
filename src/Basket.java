import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Basket implements BasketManagement {
    private List<Product> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        items.add(product);
    }

    @Override
    public void deleteProduct(Product product) {
        items.remove(product);
    }

    @Override
    public List<Product> getItems() {
        return items;
    }

    @Override
    public double getTotalPrice() {
        return items.stream().mapToDouble(Product::getProductPrice).sum();
    }

    @Override
    public void clean() {
        items.clear();
    }

    @Override
    public void interactWithUser(ProductManagement catalog, Scanner sc, int orderIdCounter) {
        while (true) {
            System.out.println("Корзина пользователя:");
            DisplayProducts.displayProducts(getItems());
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить товар в корзину.");
            System.out.println("2. Удалить товар из корзины.");
            System.out.println("3. Просмотреть общую стоимость.");
            System.out.println("4. Вернуться к основному меню.");
            System.out.print("Ввод: ");
            try {
                int basketAction = Integer.parseInt(sc.nextLine());
                switch (basketAction) {
                    case 1:
                        System.out.print("Введите название товара для добавления: ");
                        String productName = sc.nextLine();
                        Product productToAdd = catalog.getProducts().stream()
                                .filter(product -> product.getProductName().equalsIgnoreCase(productName))
                                .findFirst()
                                .orElse(null);
                        if (productToAdd != null) {
                            addProduct(productToAdd);
                            System.out.println("Товар добавлен в корзину.");
                        } else {
                            System.out.println("Товар не найден.");
                        }
                        break;
                    case 2:
                        System.out.print("Введите название товара для удаления: ");
                        String productNameToRemove = sc.nextLine();
                        Product productToRemove = catalog.getProducts().stream()
                                .filter(product -> product.getProductName().equalsIgnoreCase(productNameToRemove))
                                .findFirst()
                                .orElse(null);
                        if (productToRemove != null) {
                            deleteProduct(productToRemove);
                            System.out.println("Товар удален из корзины.");
                        } else {
                            System.out.println("Товар не найден в корзине.");
                        }
                        break;
                    case 3:
                        System.out.println("Общая стоимость корзины: " + getTotalPrice());
                        break;
                    case 4:
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