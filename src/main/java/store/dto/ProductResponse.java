package store.dto;

import store.domain.product.Product;

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
}
