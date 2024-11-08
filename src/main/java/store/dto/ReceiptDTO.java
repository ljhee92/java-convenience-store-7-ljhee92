package store.dto;

import java.util.List;

public class ReceiptDTO {
    private final List<OrderItemDTO> orderItemsDTO;
    private final int totalPrice;

    public ReceiptDTO(List<OrderItemDTO> orderItemsDTO, int totalPrice) {
        this.orderItemsDTO = orderItemsDTO;
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDTO> getOrderItemsDTO() {
        return orderItemsDTO;
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
