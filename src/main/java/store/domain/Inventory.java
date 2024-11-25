package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final Products products;
    private final List<Promotion> promotions;

    private Inventory(Products products, List<Promotion> promotions) {
        this.products = products;
        this.promotions = new ArrayList<>(promotions);
    }

    public static Inventory of(Products products, List<Promotion> promotions) {
        return new Inventory(products, promotions);
    }

    public Products getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "products=" + products +
                ", promotions=" + promotions +
                '}';
    }
}
