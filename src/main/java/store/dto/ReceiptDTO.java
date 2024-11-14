package store.dto;

import java.util.List;

public class ReceiptDTO {
    private final List<OrderItemDTO> orderItemsDTO;
    private int totalQuantity;
    private final double totalPrice;
    private final double discountForPromotion;
    private double discountForMembership;
    private double moneyForPay;

    public ReceiptDTO(List<OrderItemDTO> orderItemsDTO, int totalQuantity, double totalPrice,
                      double discountForPromotion, double discountForMembership, double moneyForPay) {
        this.orderItemsDTO = orderItemsDTO;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.discountForPromotion = discountForPromotion;
        this.discountForMembership = discountForMembership;
        this.moneyForPay = moneyForPay;
    }

    public List<OrderItemDTO> getOrderItemsDTO() {
        return orderItemsDTO;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDiscountForPromotion() {
        return discountForPromotion;
    }

    public double getDiscountForMembership() {
        return discountForMembership;
    }

    public double getMoneyForPay() {
        return moneyForPay;
    }

    public void setDiscountForMembership(double discountForMembership) {
        this.discountForMembership = discountForMembership;
    }

    public void setMoneyForPay(double moneyForPay) {
        this.moneyForPay = moneyForPay;
    }

    @Override
    public String toString() {
        return "ReceiptDTO{" +
                "orderItemsDTO=" + orderItemsDTO +
                ", totalPrice=" + totalPrice +
                ", discountForPromotion=" + discountForPromotion +
                ", discountForMembership=" + discountForMembership +
                ", moneyForPay=" + moneyForPay +
                '}';
    }
}
