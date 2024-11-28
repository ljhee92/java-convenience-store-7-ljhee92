package store.domain;

import store.dto.PurchaseResponse;

import java.math.BigDecimal;

public class Purchase {
    private final String name;
    private int buyOfTotal;
    private int buyOfGeneral;
    private int buyOfPromotion;
    private int free;
    private int freeMore;
    private int notApplicable;
    private final BigDecimal pricePerUnit;
    private final boolean onPromotion;

    private Purchase(String name, int buyOfTotal, int buyOfGeneral, int buyOfPromotion, int free, int freeMore,
                     int notApplicable, BigDecimal pricePerUnit, boolean onPromotion) {
        this.name = name;
        this.buyOfTotal = buyOfTotal;
        this.buyOfGeneral = buyOfGeneral;
        this.buyOfPromotion = buyOfPromotion;
        this.free = free;
        this.freeMore = freeMore;
        this.notApplicable = notApplicable;
        this.pricePerUnit = pricePerUnit;
        this.onPromotion = onPromotion;
    }

    public static Purchase of(String name, int buyOfGeneral, BigDecimal pricePerUnit) {
        return new Purchase(name, buyOfGeneral, buyOfGeneral, 0, 0, 0, 0, pricePerUnit, false);
    }

    public static Purchase ofOnPromotion(String name, int buyOfTotal, int buyOfGeneral, int buyOfPromotion,
                                         int free, BigDecimal pricePerUnit) {
        return new Purchase(name, buyOfTotal, buyOfGeneral, buyOfPromotion, free, 0, 0, pricePerUnit, true);
    }

    public void addFreeMore(int freeMore) {
        this.freeMore += freeMore;
        this.buyOfTotal += freeMore;
        this.buyOfPromotion += freeMore;
        this.free += freeMore;
    }

    public void setNotApplicable(int notApplicable) {
        this.notApplicable += notApplicable;
    }

    public void minusNotApplicable(int notApplicableOfGeneral, int notApplicableOfPromotion) {
        this.notApplicable -= (notApplicableOfGeneral + notApplicableOfPromotion);
        this.buyOfTotal -= (notApplicableOfGeneral + notApplicableOfPromotion);
        this.buyOfGeneral -= notApplicableOfGeneral;
        this.buyOfPromotion -= notApplicableOfPromotion;
    }

    public BigDecimal getTotalPrice() {
        return pricePerUnit.multiply(BigDecimal.valueOf(buyOfTotal));
    }

    public BigDecimal getApplyPromotionPrice() {
        return pricePerUnit.multiply(BigDecimal.valueOf(buyOfPromotion));
    }

    public BigDecimal getFreePromotionPrice() {
        return pricePerUnit.multiply(BigDecimal.valueOf(free));
    }

    public PurchaseResponse toResponse() {
        return new PurchaseResponse(name, buyOfTotal, buyOfGeneral, buyOfPromotion, free, pricePerUnit);
    }

    public int getGeneralQuantity() {
        return buyOfGeneral;
    }

    public String getName() {
        return name;
    }

    public int getBuyQuantity() {
        return buyOfTotal;
    }

    public boolean isOnPromotion() {
        return onPromotion;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "name='" + name + '\'' +
                ", buyOfTotal=" + buyOfTotal +
                ", buyOfGeneral=" + buyOfGeneral +
                ", buyOfPromotion=" + buyOfPromotion +
                ", free=" + free +
                ", freeMore=" + freeMore +
                ", notApplicable=" + notApplicable +
                ", pricePerUnit=" + pricePerUnit +
                ", onPromotion=" + onPromotion +
                '}';
    }
}
