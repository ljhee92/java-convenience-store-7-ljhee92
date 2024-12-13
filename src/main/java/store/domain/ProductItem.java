package store.domain;

import java.math.BigDecimal;

public record ProductItem(String name, BigDecimal price, int quantity, String promotionName) {
    public static ProductItem from(Product product) {
        return new ProductItem(product.getName(), product.getPrice(),
                product.getQuantity(), getPromotionName(product.getPromotion())
                );
    }

    private static String getPromotionName(Promotion promotion) {
        if (promotion == null) {
            return "";
        }
        return promotion.getName();
    }
}
