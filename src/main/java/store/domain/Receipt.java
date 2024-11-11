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

    private int calculateTotalQuantity() {
        int totalQuantity = 0;
        for (OrderItemDTO orderItemDTO : orderItemsDTO) {
            totalQuantity += orderItemDTO.getTotalQuantity();
        }
        return totalQuantity;
    }

    private double calculateTotalPrice() {
        double totalPrice = 0;
        for (OrderItemDTO orderItemDTO : orderItemsDTO) {
            totalPrice += orderItemDTO.getTotalQuantity() * orderItemDTO.getPricePerProduct();
        }
        return totalPrice;
    }

    private double calculateDiscountForPromotion() {
        double discountForPromotion = 0;
        for (OrderItemDTO orderItemDTO : orderItemsDTO) {
            discountForPromotion += orderItemDTO.getFreeQuantity() * orderItemDTO.getPricePerProduct();
        }
        return discountForPromotion;
    }

    private double calculateDiscountForMembership() {
        double totalPrice = calculateTotalPrice();
        double beforeDiscountForPromotion = 0;
        for (OrderItemDTO orderItemDTO : orderItemsDTO) {
            beforeDiscountForPromotion
                    += orderItemDTO.getOrderedPromotionQuantity() * orderItemDTO.getPricePerProduct();
        }
        double afterDiscountForPromotion = totalPrice - beforeDiscountForPromotion;
        return ratioDiscount.getDiscountPrice(afterDiscountForPromotion);
    }

    private double calculateMoneyForPay() {
        return calculateTotalPrice() - calculateDiscountForPromotion() - calculateDiscountForMembership();
    }

    public ReceiptDTO toDTO() {
        return new ReceiptDTO(orderItemsDTO, calculateTotalQuantity(), calculateTotalPrice(),
                calculateDiscountForPromotion(), calculateDiscountForMembership(), calculateMoneyForPay());
    }
}
