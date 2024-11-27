package store.domain;

import java.math.BigDecimal;

public class Purchase {
    private final String name;
    private int buyQuantity;
    private int freeQuantity;
    private int notApplicableQuantity;
    private final BigDecimal pricePerUnit;
    private boolean onPromotion;

    private Purchase(String name, int buyQuantity, int freeQuantity, int notApplicableQuantity,
                     BigDecimal pricePerUnit, boolean onPromotion) {
        this.name = name;
        this.buyQuantity = buyQuantity;
        this.freeQuantity = freeQuantity;
        this.notApplicableQuantity = notApplicableQuantity;
        this.pricePerUnit = pricePerUnit;
        this.onPromotion = onPromotion;
    }

    public static Purchase of(String name, int buyQuantity, BigDecimal pricePerUnit) {
        return new Purchase(name, buyQuantity, 0, 0, pricePerUnit, false);
    }

    public static Purchase ofOnPromotion(String name, int buyQuantity, BigDecimal pricePerUnit) {
        return new Purchase(name, buyQuantity, 0, 0, pricePerUnit, true);
    }

    public void addFreeQuantity(int freeQuantity) {
        this.freeQuantity += freeQuantity;
    }

    public void updateNotApplicableQuantity(int notApplicableQuantity) {
        this.buyQuantity -= notApplicableQuantity;
        this.notApplicableQuantity += notApplicableQuantity;
    }

    public int getApplyPromotionQuantity() {
        int applyPromotionQuantity = 0;
        if (onPromotion) {
            applyPromotionQuantity = buyQuantity - notApplicableQuantity;
        }
        return applyPromotionQuantity;
    }

    public String getName() {
        return name;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }
}
