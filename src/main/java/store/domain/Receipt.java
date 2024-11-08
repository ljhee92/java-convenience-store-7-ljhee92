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

    private List<OrderItemDTO> orderInfoByOrder() {
        List<OrderItemDTO> orderItemsByOrderDTODTO = new ArrayList<>();
        for (Order order : orders) {
            OrderItemDTO orderItemDTO = new OrderItemDTO(order.getName(), order.getQuantity(),
                    order.getPurchaseAmount());
            orderItemsByOrderDTODTO.add(orderItemDTO);
        }
        return orderItemsByOrderDTODTO;
    }

    private int calculateTotalAmount() {
        int totalAmount = 0;
        for (Order order : orders) {
            totalAmount += order.getPurchaseAmount();
        }
        return totalAmount;
    }

    public ReceiptDTO toReceiptDTO() {
        return new ReceiptDTO(orderInfoByOrder(), calculateTotalAmount());
    }
}
