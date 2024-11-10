package store.domain;

import store.dto.ProductDTO;

import java.util.Objects;

public class Product {
    private final String name;
    private final int price;
    private int stock;
    private final String promotion;

    public Product(String name, int price, int stock, String promotion) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.promotion = promotion;
    }

    public boolean exist(String name) {
        return this.name.equals(name);
    }

    public boolean onPromotion() {
        return !"null".equals(this.promotion);
    }

    public ProductDTO toDTO() {
        return new ProductDTO(this.name, this.price, this.stock, this.promotion);
    }

    public int reduceStock(int orderQuantity) {
        if (onPromotion() && this.stock < orderQuantity) {
            int remainingStock = orderQuantity - this.stock;
            this.stock = 0;
            return remainingStock;
        }

        int remainingStock = - orderQuantity;
        this.stock -= orderQuantity;
        return remainingStock;
    }

    public void reduceStockForFree(int freeMoreQuantity) {
        this.stock -= freeMoreQuantity;
    }

    public void resetStockForNotPurchase(int notPurchasedQuantity) {
        this.stock += notPurchasedQuantity;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getPromotion() {
        return promotion;
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

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", promotion='" + promotion + '\'' +
                '}';
    }
}
