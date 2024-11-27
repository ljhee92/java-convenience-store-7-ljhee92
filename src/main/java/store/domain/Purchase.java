package store.domain;

import java.math.BigDecimal;

public class Purchase {
    private final String name;
    private int buyQuantity;
    private int freeQuantity;
    private final BigDecimal pricePerUnit;

    private Purchase(String name, int buyQuantity, int freeQuantity, BigDecimal pricePerUnit) {
        this.name = name;
        this.freeQuantity = freeQuantity;
        this.buyQuantity = buyQuantity;
        this.pricePerUnit = pricePerUnit;
    }

    public static Purchase of(String name, int buyQuantity, BigDecimal pricePerUnit) {
        return new Purchase(name, buyQuantity, 0, pricePerUnit);
    }

    public void addFreeQuantity(int freeQuantity) {
        this.freeQuantity += freeQuantity;
    }

    public String getName() {
        return name;
    }

    public int getBuyQuantity() {
        return buyQuantity;
    }
}
