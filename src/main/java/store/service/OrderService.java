package store.service;

import store.domain.Order;
import store.domain.Products;
import store.util.Parser;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public List<Order> takeOrders(List<List<String>> inputOrders, Products products) {
        List<Order> orders = new ArrayList<>();
        inputOrders.forEach(inputOrder -> addOrders(orders, inputOrder, products));
        return orders;
    }

    private void addOrders(List<Order> orders, List<String> inputOrder, Products products) {
        String name = inputOrder.getFirst();
        int quantity = Parser.stringToInt(inputOrder.getLast());
        Order order = new Order(name, quantity, products);
        orders.add(order);
    }
}
