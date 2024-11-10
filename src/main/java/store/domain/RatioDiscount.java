package store.domain;

public enum RatioDiscount implements DiscountStrategy {
    MEMBERSHIP(30);

    private static final int ONE = 1;
    private static final double PERCENTAGE = 0.01;

    private final double discountRatio;

    RatioDiscount(double discountRatio) {
        this.discountRatio = discountRatio;
    }

    @Override
    public double getDiscountPrice(double price) {
        double discountForMembership = price * (discountRatio * PERCENTAGE);

        if (discountForMembership > 8000) {
            discountForMembership = 8000;
        }
        return discountForMembership;
    }
}
