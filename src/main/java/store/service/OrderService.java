package store.service;

import store.domain.Order;
import store.domain.Products;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OrderService {
    public List<Order> takeOrders(List<Map<String, String>> inputOrders, Products products) {
        List<Order> orders = new ArrayList<>();
        inputOrders.forEach(inputOrder -> addOrders(orders, inputOrder, products));
        return orders;
    }

    private void addOrders(List<Order> orders, Map<String, String> inputOrder, Products products) {
        Set<String> inputProductNames = inputOrder.keySet();
        for (String name : inputProductNames) {
            int quantity = Integer.parseInt(inputOrder.get(name));
            Order order = new Order(name, quantity, products);
            orders.add(order);
        }
    }
}
