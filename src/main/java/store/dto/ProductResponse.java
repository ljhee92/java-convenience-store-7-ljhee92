package store.dto;

import store.domain.Product;

import java.math.BigDecimal;

public record ProductResponse(
        String name,
        BigDecimal price,
        int quantity,
        String promotion
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getName(), product.getPrice(),
                product.getQuantity(), product.getPromotion()
        );
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public int quantity() {
        return quantity;
    }

    @Override
    public String promotion() {
        return promotion;
    }
}
