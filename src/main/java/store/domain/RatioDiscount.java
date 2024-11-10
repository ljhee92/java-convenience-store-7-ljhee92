package store.domain;

public enum RatioDiscount implements DiscountStrategy {
    MEMBERSHIP(30);

    private static final int ONE = 1;
    private static final int ONE_HUNDRED = 100;

    private final int discountRatio;

    RatioDiscount(int discountRatio) {
        this.discountRatio = discountRatio;
    }

    @Override
    public int getDiscountPrice(int price) {
        int discountForMembership = price * (discountRatio / ONE_HUNDRED);

        if (discountForMembership > 8000) {
            discountForMembership = 8000;
        }
        return discountForMembership;
    }
}
