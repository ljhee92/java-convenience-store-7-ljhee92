package store.domain;

import store.dto.OrderItemDTO;
import store.dto.ReceiptDTO;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final Orders orders;

    public Receipt(Orders orders) {
        this.orders = orders;
    }

    private List<OrderItemDTO> getOrderItems() {
        List<OrderItemDTO> orderItemsDTO = new ArrayList<>();
        orders.forEach(order -> {
            OrderItemDTO orderItemDTO = new OrderItemDTO(order.getName(), order.getQuantity(),
                    order.getPurchaseAmount());
            orderItemsDTO.add(orderItemDTO);
        });
        return orderItemsDTO;
    }

    private int calculateTotalAmount() {
        int totalAmount = 0;
        for (Order order : orders) {
            totalAmount += order.getPurchaseAmount();
        }
        return totalAmount;
    }

    public ReceiptDTO toDTO() {
        return new ReceiptDTO(getOrderItems(), calculateTotalAmount());
    }
}
