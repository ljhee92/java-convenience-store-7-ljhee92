package store.domain;

public enum RatioDiscount implements DiscountStrategy {
    MEMBERSHIP(30);

    private static final double PERCENTAGE = 0.01;
    private static final int MINIMUM_DISCOUNT_AMOUNT = 8_000;

    private final double discountRatio;

    RatioDiscount(double discountRatio) {
        this.discountRatio = discountRatio;
    }

    @Override
    public double getDiscountPrice(double price) {
        double discountForMembership = price * (discountRatio * PERCENTAGE);

        if (discountForMembership > MINIMUM_DISCOUNT_AMOUNT) {
            discountForMembership = MINIMUM_DISCOUNT_AMOUNT;
        }

        return discountForMembership;
    }
}
