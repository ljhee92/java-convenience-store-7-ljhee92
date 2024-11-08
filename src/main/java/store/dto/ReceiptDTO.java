package store.dto;

import java.util.List;

public class ReceiptDTO {
    private final List<OrderItemDTO> orderItemDTOS;
    private final int totalPrice;

    public ReceiptDTO(List<OrderItemDTO> orderItemDTOS, int totalPrice) {
        this.orderItemDTOS = orderItemDTOS;
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDTO> getOrderItemDTOS() {
        return orderItemDTOS;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDiscountForPromotion() {
        return 0;
    }

    public int getDiscountForMembership() {
        return 0;
    }

    public int getMoneyForPay() {
        return totalPrice - getDiscountForPromotion() - getDiscountForMembership();
    }
}
