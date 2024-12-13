package store.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final int quantity;
    private final Promotion promotion;

    private Product(String name, BigDecimal price, int quantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public static Product of(String name, BigDecimal price, int quantity) {
        return new Product(name, price, quantity, null);
    }

    public static Product of(String name, BigDecimal price, int quantity, Promotion promotion) {
        return new Product(name, price, quantity, promotion);
    }

    public static Product empty(String name, BigDecimal price) {
        return new Product(name, price, 0, null);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", promotion=" + promotion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
