package store.view;

import store.dto.ProductResponse;
import store.util.OutputWriter;

import java.util.List;

public class OutputView implements OutputWriter {
    public void displayProductsForPurchase(List<ProductResponse> productResponses) {
        displayFormat("%s%n", "안녕하세요. W편의점입니다.");
        displayFormat("%s%n", "현재 보유하고 있는 상품입니다.");
        productResponses.forEach(productResponse -> {
            displayFormat("- %s %,.0f원 %s %s%n", productResponse.name(), productResponse.price(),
                    convertToQuantityFormat(productResponse.quantity()),
                    convertToPromotionFormat(productResponse.promotion()));
        });
        displayNewLine();
    }

    private static String convertToQuantityFormat(int originalQuantity) {
        String quantity = String.format("%,d개", originalQuantity);
        if ("0개".equals(quantity)) {
            quantity = "재고 없음";
        }
        return quantity;
    }

    private static String convertToPromotionFormat(String originalPromotion) {
        if ("null".equals(originalPromotion)) {
            originalPromotion = "";
        }
        return originalPromotion;
    }
}
