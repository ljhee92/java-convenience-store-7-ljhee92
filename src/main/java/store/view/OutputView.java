package store.view;

import store.dto.ProductDTO;
import store.dto.ReceiptDTO;

import java.util.List;

public class OutputView {
    public void displayWelcomeAndProducts(List<ProductDTO> productsDTO) {
        System.out.printf("%s%n", "안녕하세요. W편의점입니다.");
        System.out.printf("%s%n%n", "현재 보유하고 있는 상품입니다.");
        productsDTO.forEach(productDTO -> {
            System.out.printf("- %s %,d원 %s %s%n", productDTO.getName(), productDTO.getPrice(),
                    getProductQuantityFormat(productDTO), productDTO.getPromotion());
        });
        System.out.println();
    }

    private String getProductQuantityFormat(ProductDTO productDTO) {
        String quantityFormat = String.format("%,d개", productDTO.getQuantity());
        if (productDTO.getQuantity() == 0) {
            quantityFormat = "재고 없음";
        }
        return quantityFormat;
    }

    public void displayReceipt(ReceiptDTO receiptDTO) {
        System.out.println("==============W 편의점================");
        System.out.println("상품명\t\t\t수량\t\t\t금액");
        displayOrderItemsDTO(receiptDTO);
        System.out.println("=============증\t\t정===============");
        displayPromotionItemsDTO(receiptDTO);
        System.out.println("====================================");
        System.out.printf("%s\t\t\t%d\t\t\t%,.0f%n", "총구매액", receiptDTO.getTotalQuantity(), receiptDTO.getTotalPrice());
        System.out.printf("%s\t\t\t\t\t\t-%,.0f%n", "행사할인", receiptDTO.getDiscountForPromotion());
        System.out.printf("%s\t\t\t\t\t\t-%,.0f%n", "멤버십할인", receiptDTO.getDiscountForMembership());
        System.out.printf("%s\t\t\t\t\t\t%,.0f%n", "내실돈", receiptDTO.getMoneyForPay());
    }

    private void displayOrderItemsDTO(ReceiptDTO receiptDTO) {
        receiptDTO.getOrderItemsDTO().forEach(orderItemDTO -> {
            System.out.printf("%-15s", orderItemDTO.getName());
            System.out.printf("%-5d", orderItemDTO.getTotalQuantity());
            System.out.printf("%,12.0f%n", (orderItemDTO.getTotalQuantity() * orderItemDTO.getPricePerProduct()));;
        });
    }

    private void displayPromotionItemsDTO(ReceiptDTO receiptDTO) {
        receiptDTO.getOrderItemsDTO().forEach(orderItemDTO -> {
            if (orderItemDTO.getFreeQuantity() != 0) {
                System.out.printf("%-15s", orderItemDTO.getName());
                System.out.printf("%-5d%n", orderItemDTO.getFreeQuantity());
            }
        });
    }
}
