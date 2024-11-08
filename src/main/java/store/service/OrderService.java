package store.service;

import store.domain.Order;
import store.domain.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {
    public List<Order> takeOrders(List<Map<String, String>> inputOrders, Products products) {
        List<Order> orders = new ArrayList<>();

        inputOrders.forEach(inputOrder -> {
            for (String name : inputOrder.keySet()) {
                int quantity = Integer.parseInt(inputOrder.get(name));
                Order order = new Order(name, quantity, products);
                orders.add(order);
            }
        });
        return orders;
    }
}
