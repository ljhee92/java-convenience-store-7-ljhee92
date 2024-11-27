package store.domain;

import java.math.BigDecimal;

public class Membership {
    private static final double DISCOUNT_RATIO = 0.3;
    private static final BigDecimal MINIMUM_DISCOUNT_AMOUNT = BigDecimal.valueOf(8_000);

    public BigDecimal getDiscountAmount(BigDecimal amount) {
        return amount.multiply(BigDecimal.valueOf(DISCOUNT_RATIO)).min(MINIMUM_DISCOUNT_AMOUNT);
    }
}
