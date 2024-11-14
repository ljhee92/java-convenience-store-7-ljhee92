package store.view;

import store.dto.ProductDTO;
import store.dto.ReceiptDTO;
import store.util.OutputWriter;

import java.util.List;

public class OutputView implements OutputWriter {
    private static final int ZERO = 0;

    public void displayWelcomeAndProducts(List<ProductDTO> productsDTO) {
        displayFormat("%s%n", "안녕하세요. W편의점입니다.");
        displayFormat("%s%n%n", "현재 보유하고 있는 상품입니다.");

        productsDTO.forEach(productDTO -> {
            displayFormat("- %s %,d원 %s %s%n", productDTO.getName(), productDTO.getPrice(),
                    convertToQuantityFormat(productDTO), productDTO.getPromotion());
        });

        displayNewLine();
    }

    private String convertToQuantityFormat(ProductDTO productDTO) {
        String quantityFormat = String.format("%,d개", productDTO.getQuantity());

        if (productDTO.getQuantity() == ZERO) {
            quantityFormat = "재고 없음";
        }

        return quantityFormat;
    }

    public void displayReceipt(ReceiptDTO receiptDTO) {
        displayFormat("%n%s%n", "==============W 편의점================");
        displayMessage("상품명\t\t\t수량\t\t\t금액");
        displayOrderItems(receiptDTO);
        displayMessage("=============증\t\t정===============");
        displayPromotionItems(receiptDTO);
        displayMessage("====================================");
        displayFormat("%s\t\t\t%d\t\t\t%,.0f%n", "총구매액", receiptDTO.getTotalQuantity(), receiptDTO.getTotalPrice());
        displayFormat("%s\t\t\t\t\t\t-%,.0f%n", "행사할인", receiptDTO.getDiscountForPromotion());
        displayFormat("%s\t\t\t\t\t\t-%,.0f%n", "멤버십할인", receiptDTO.getDiscountForMembership());
        displayFormat("%s\t\t\t\t\t\t%,.0f%n", "내실돈", receiptDTO.getMoneyForPay());
    }

    private void displayOrderItems(ReceiptDTO receiptDTO) {
        receiptDTO.getOrderItemsDTO().forEach(orderItemDTO -> {
            displayFormat("%-15s", orderItemDTO.getName());
            displayFormat("%-5d", orderItemDTO.getTotalQuantity());
            displayFormat("%,12.0f%n", (orderItemDTO.getTotalQuantity() * orderItemDTO.getPricePerProduct()));
        });
    }

    private void displayPromotionItems(ReceiptDTO receiptDTO) {
        receiptDTO.getOrderItemsDTO().forEach(orderItemDTO -> {
            if (orderItemDTO.getFreeQuantity() != ZERO) {
                displayFormat("%-15s", orderItemDTO.getName());
                displayFormat("%-5d%n", orderItemDTO.getFreeQuantity());
            }
        });
    }
}
