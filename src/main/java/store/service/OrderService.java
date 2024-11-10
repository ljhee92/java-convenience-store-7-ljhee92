package store.service;

import store.domain.Order;
import store.domain.Orders;
import store.domain.Products;
import store.domain.Promotions;
import store.domain.Receipt;
import store.dto.OrderItemDTO;
import store.dto.ReceiptDTO;
import store.util.Parser;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private Orders orders;

    public void takeOrders(List<List<String>> inputOrders, Products products, Promotions promotions) {
        orders = new Orders(new ArrayList<>());
        inputOrders.forEach(inputOrder -> {

            String name = inputOrder.getFirst();
            int quantity = Parser.stringToInt(inputOrder.getLast());
            Order order = new Order(name, quantity, products, promotions);

            orders.add(order);
        });
    }

    public List<OrderItemDTO> progressOrders() {
        return orders.reduceStock();
    }

    public ReceiptDTO createReceipt(List<OrderItemDTO> orderItemsDTO) {
        Receipt receipt = new Receipt(orderItemsDTO);
        return receipt.toDTO();
    }
}
