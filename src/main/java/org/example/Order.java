package org.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Order {
    private List<Product> products;
    private double totalPrice;
    private String status;
    private String orderDate;

    public Order(Cart cart, String orderDate) {
        this.products = new ArrayList<>(cart.getProducts());
        this.totalPrice = cart.getTotalPrice();
        this.status = "Нове";
        this.orderDate = orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Замовлення:\n");
        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }
        sb.append("Загальна вартість: ").append(totalPrice).append("\n");
        sb.append("Статус: ").append(status).append("\n");
        sb.append("Дата замовлення: ").append(orderDate);
        return sb.toString();
    }
}
