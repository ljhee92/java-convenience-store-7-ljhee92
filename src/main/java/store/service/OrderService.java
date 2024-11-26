package store.service;

import store.domain.Order;
import store.domain.Orders;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final StoreService storeService;

    public OrderService(StoreService storeService) {
        this.storeService = storeService;
    }

    public Orders createOrders(List<String> inputOrders) {
        List<String> parsedOrders = parseOrder(inputOrders);
        List<Order> orders = new ArrayList<>();
        for (String orderItem : parsedOrders) {
            String name = orderItem.split("-")[0];
            int quantity = Integer.parseInt(orderItem.split("-")[1]);
            Order order = Order.of(name, quantity, storeService.getProductsOfInventory());
            orders.add(order);
        }
        return Orders.of(orders);
    }

    private List<String> parseOrder(List<String> inputOrders) {
        return inputOrders.stream()
                .map(order -> order = order.replaceAll("\\[", "").replaceAll("]", ""))
                .toList();
    }
}
