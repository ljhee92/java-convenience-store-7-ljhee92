package store.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final String name;
    private final BigDecimal price;
    private final int quantity;
    private final String promotion;

    private Product(String name, BigDecimal price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public static Product of(String name, BigDecimal price) {
        return new Product(name, price, 0, "null");
    }

    public static Product of(String name, BigDecimal price, int quantity, String promotion) {
        return new Product(name, price, quantity, promotion);
    }

    public boolean onPromotion() {
        return !"null".equals(promotion);
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

    public String getPromotion() {
        return promotion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", promotion='" + promotion + '\'' +
                '}';
    }
}
