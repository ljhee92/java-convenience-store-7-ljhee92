package store.domain;

import java.math.BigDecimal;

public class Calculator {
    private final Purchases purchases;
    private final Membership membership;

    private Calculator(Purchases purchases, Membership membership) {
        this.purchases = purchases;
        this.membership = membership;
    }

    public static Calculator of(Purchases purchases, Membership membership) {
        return new Calculator(purchases, membership);
    }

    public BigDecimal calculateMembershipDiscountAmount() {
        BigDecimal membershipDiscountAmount = BigDecimal.ZERO;
        BigDecimal applyAmount = BigDecimal.ZERO;
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Purchase purchase : purchases) {
            totalAmount = totalAmount.add(purchase.getPricePerUnit().multiply(BigDecimal.valueOf(purchase.getBuyQuantity())));
            applyAmount = applyAmount.add(purchase.getPricePerUnit().multiply(BigDecimal.valueOf(purchase.getApplyPromotionQuantity())));
        }
        membershipDiscountAmount = membershipDiscountAmount.add(membership.getDiscountAmount(totalAmount.subtract(applyAmount)));
        return membershipDiscountAmount;
    }
}
