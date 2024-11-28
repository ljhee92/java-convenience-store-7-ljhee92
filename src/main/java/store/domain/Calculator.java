package store.domain;

import store.dto.Receipt;

import java.math.BigDecimal;

public class Calculator {
    private final Purchases purchases;
    private final Membership membership;

    private Calculator(Purchases purchases, Membership membership) {
        this.purchases = purchases;
        this.membership = membership;
    }

    public static Calculator of(Purchases purchases) {
        return new Calculator(purchases, null);
    }

    public static Calculator of(Purchases purchases, Membership membership) {
        return new Calculator(purchases, membership);
    }

    public Receipt issueReceipt() {
        return new Receipt(purchases.toResponse(), purchases.getTotalQuantity(), purchases.getTotalPrice(),
                purchases.getFreePromotionPrice(), getMembershipDiscountAmount(), getPayPrice());
    }

    private BigDecimal getMembershipDiscountAmount() {
        BigDecimal discountAmount = BigDecimal.ZERO;
        BigDecimal totalPrice = purchases.getTotalPrice();
        BigDecimal applyPromotionPrice = purchases.getApplyPromotionPrice();
        if (membership != null) {
            discountAmount = membership.getDiscountAmount(totalPrice.subtract(applyPromotionPrice));
        }
        return discountAmount;
    }

    private BigDecimal getPayPrice() {
        return purchases.getTotalPrice().subtract(purchases.getFreePromotionPrice())
                .subtract(getMembershipDiscountAmount());
    }
}
