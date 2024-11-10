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
        System.out.println("===========W 편의점=============");
        System.out.println("상품명\t\t수량\t\t\t금액");
        displayOrderItemsDTO(receiptDTO);
        System.out.println("===========증\t정=============");
        System.out.println("==============================");
        System.out.println("총구매액\t\t\t\t\t" + String.format("%,d", receiptDTO.getTotalPrice()));
        System.out.println("행사할인\t\t\t\t\t" + String.format("-%,d", receiptDTO.getDiscountForPromotion()));
        System.out.println("멤버십할인\t\t\t\t\t" + String.format("-%,d", receiptDTO.getDiscountForMembership()));
        System.out.println("내실돈\t\t\t\t\t" + String.format("%,d",receiptDTO.getMoneyForPay()));
    }

    private void displayOrderItemsDTO(ReceiptDTO receiptDTO) {
        receiptDTO.getOrderItemsDTO().forEach(orderItemDTO -> {
            System.out.println(orderItemDTO.getName() + "\t\t" + orderItemDTO.getTotalQuantity()
                    + "\t\t\t" + orderItemDTO.getPricePerProduct());
        });
    }

}
