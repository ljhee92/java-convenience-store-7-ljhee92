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
        System.out.println(orderItemsDTO);

//      [OrderItemDTO{name='콜라', totalQuantity=3, pricePerProduct=1000.0, orderedPromotionQuantity=3, orderedNotPromotionQuantity=0, freeMoreQuantity=0, notApplicablePromotionQuantity=0, freeQuantity=1},
//      OrderItemDTO{name='에너지바', totalQuantity=5, pricePerProduct=2000.0, orderedPromotionQuantity=0, orderedNotPromotionQuantity=5, freeMoreQuantity=0, notApplicablePromotionQuantity=0, freeQuantity=0}]
//      [OrderItemDTO{name='콜라', totalQuantity=10, pricePerProduct=1000.0, orderedPromotionQuantity=7, orderedNotPromotionQuantity=3, freeMoreQuantity=1, notApplicablePromotionQuantity=4, freeQuantity=2}]
//      [OrderItemDTO{name='콜라', totalQuantity=10, pricePerProduct=1000.0, orderedPromotionQuantity=7, orderedNotPromotionQuantity=3, freeMoreQuantity=1, notApplicablePromotionQuantity=4, freeQuantity=2}]
//      [OrderItemDTO{name='오렌지주스', totalQuantity=2, pricePerProduct=1800.0, orderedPromotionQuantity=1, orderedNotPromotionQuantity=0, freeMoreQuantity=1, notApplicablePromotionQuantity=1, freeQuantity=1}]

        for (OrderItemDTO orderItemDTO : orderItemsDTO) {
//            if (orderItemDTO.getFreeQuantity() != 0) {
                beforeDiscountForPromotion
                        += orderItemDTO.getOrderedPromotionQuantity() * orderItemDTO.getPricePerProduct();
//            }
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
