package store.domain;

import store.dto.ProductDTO;

import java.util.Objects;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private final String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public boolean exist(String name) {
        return this.name.equals(name);
    }

    public boolean inProgressPromotion(String name) {
        return !"null".equals(this.promotion);
    }

    public String getName() {
        return name;
    }

    public int getQuantity(String name) {
        return this.quantity;
    }

    public ProductDTO toDTO() {
        return new ProductDTO(this.name, this.price, this.quantity, this.promotion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
