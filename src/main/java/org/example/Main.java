package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Категорії та продукти
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Смартфон", 12999.50, "Смартфон з великим екраном та високою автономністю", smartphones);
        Product product3 = new Product(3, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);

        Product[] products = {product1, product2, product3};

        Cart cart = new Cart();
        OrderHistory orderHistory = new OrderHistory();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Додати товар до кошика");
            System.out.println("3 - Переглянути кошик");
            System.out.println("4 - Зробити замовлення");
            System.out.println("5 - Видалити товар з кошика");
            System.out.println("6 - Переглянути історію замовлень");
            System.out.println("7 - Пошук товарів за категорією");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;

                case 2:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int idToAdd = scanner.nextInt();
                    if (idToAdd >= 1 && idToAdd <= products.length) {
                        cart.addProduct(products[idToAdd - 1]);
                        System.out.println("Товар додано до кошика.");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;

                case 3:
                    System.out.println(cart);
                    break;

                case 4:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart, java.time.LocalDate.now().toString());
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        orderHistory.addOrder(order);
                        cart.clear();
                    }
                    break;

                case 5:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int idToRemove = scanner.nextInt();
                    boolean removed = cart.removeProductById(idToRemove);
                    if (removed) {
                        System.out.println("Товар видалено з кошика.");
                    } else {
                        System.out.println("Товар з таким ID не знайдено в кошику.");
                    }
                    break;

                case 6:
                    System.out.println(orderHistory);
                    break;

                case 7: // Пошук товарів за категорією
                    System.out.println("Виберіть категорію:");
                    System.out.println("1 - Електроніка");
                    System.out.println("2 - Смартфони");
                    System.out.println("3 - Аксесуари");

                    int categoryChoice = scanner.nextInt();
                    Category selectedCategory = null;

                    switch (categoryChoice) {
                        case 1:
                            selectedCategory = electronics;
                            break;
                        case 2:
                            selectedCategory = smartphones;
                            break;
                        case 3:
                            selectedCategory = accessories;
                            break;
                        default:
                            System.out.println("Невідома категорія.");
                    }

                    if (selectedCategory != null) {
                        System.out.println("Товари у вибраній категорії:");
                        for (Product product : products) {
                            if (product.getCategory().equals(selectedCategory)) {
                                System.out.println(product);
                            }
                        }
                    }
                    break;

                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;

                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }
        }
    }
}
