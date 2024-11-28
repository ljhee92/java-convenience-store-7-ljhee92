package store.view;

import store.dto.ProductResponse;
import store.dto.PurchaseResponse;
import store.dto.Receipt;
import store.util.OutputWriter;

import java.math.BigDecimal;
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

    public void displayReceipt(Receipt receipt) {
        displayMessage("==============W 편의점================");
        displayMessage("상품명\t\t\t수량\t\t\t금액");
        displayPurchaseResponses(receipt.purchaseResponses());
        displayMessage("=============증\t\t정===============");
        displayPromotionResponses(receipt.purchaseResponses());
        displayMessage("====================================");
        displayFormat("총구매액\t\t\t%d\t\t\t%,.0f%n",receipt.totalQuantity(), receipt.totalPrice());
        displayFormat("행사할인\t\t\t\t\t\t-%,.0f%n", receipt.discountPromotionPrice());
        displayFormat("멤버십할인\t\t\t\t\t\t-%,.0f%n", receipt.discountMembershipPrice());
        displayFormat("내실돈\t\t\t\t\t\t%,.0f%n", receipt.payPrice());
    }

    private void displayPurchaseResponses(List<PurchaseResponse> purchaseResponses) {
        purchaseResponses.forEach(purchaseResponse -> {
            displayFormat("%-15s", purchaseResponse.name());
            displayFormat("%-5d", purchaseResponse.total());
            displayFormat("%,12.0f%n",
                    purchaseResponse.pricePerUnit().multiply(BigDecimal.valueOf(purchaseResponse.total())));
        });
    }

    private void displayPromotionResponses(List<PurchaseResponse> purchaseResponses) {
        purchaseResponses.forEach(purchaseResponse -> {
            if (purchaseResponse.free() != 0) {
                displayFormat("%-15s", purchaseResponse.name());
                displayFormat("%-5d%n", purchaseResponse.free());
            }
        });
    }
}
