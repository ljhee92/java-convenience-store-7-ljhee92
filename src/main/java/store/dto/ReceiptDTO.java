package store.dto;

import java.util.List;

public class ReceiptDTO {
    private final List<OrderItemDTO> orderItemsDTO;
    private final int totalPrice;
    private final int discountForPromotion;
    private final int discountForMembership;
    private final int moneyForPay;

    public ReceiptDTO(List<OrderItemDTO> orderItemsDTO, int totalPrice,
                      int discountForPromotion, int discountForMembership, int moneyForPay) {
        this.orderItemsDTO = orderItemsDTO;
        this.totalPrice = totalPrice;
        this.discountForPromotion = discountForPromotion;
        this.discountForMembership = discountForMembership;
        this.moneyForPay = moneyForPay;
    }

    public List<OrderItemDTO> getOrderItemsDTO() {
        return orderItemsDTO;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDiscountForPromotion() {
        return discountForPromotion;
    }

    public int getDiscountForMembership() {
        return discountForMembership;
    }

    public int getMoneyForPay() {
        return moneyForPay;
    }
}
