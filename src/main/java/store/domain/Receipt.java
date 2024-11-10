package store.domain;

import store.dto.OrderItemDTO;
import store.dto.ReceiptDTO;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<OrderItemDTO> orderItemsDTO;
    private final RatioDiscount ratioDiscount = RatioDiscount.MEMBERSHIP;

    public Receipt(List<OrderItemDTO> orderItemsDTO) {
        this.orderItemsDTO = new ArrayList<>(orderItemsDTO);
    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        for (OrderItemDTO orderItemDTO : orderItemsDTO) {
            totalPrice += orderItemDTO.getTotalQuantity() * orderItemDTO.getPricePerProduct();
        }
        return totalPrice;
    }

    private int calculateDiscountForPromotion() {
        int discountForPromotion = 0;
        for (OrderItemDTO orderItemDTO : orderItemsDTO) {
            discountForPromotion += orderItemDTO.getFreeQuantity() * orderItemDTO.getPricePerProduct();
        }
        return discountForPromotion;
    }

    private int calculateDiscountForMembership() {
        int totalPrice = calculateTotalPrice();
        int afterDiscountForPromotion = totalPrice - calculateDiscountForPromotion();
        return ratioDiscount.getDiscountPrice(afterDiscountForPromotion);
    }

    private int calculateMoneyForPay() {
        return calculateTotalPrice() - calculateDiscountForPromotion() - calculateDiscountForMembership();
    }

    public ReceiptDTO toDTO() {
        return new ReceiptDTO(orderItemsDTO, calculateTotalPrice(), calculateDiscountForPromotion(),
                calculateDiscountForMembership(), calculateMoneyForPay());
    }
}
