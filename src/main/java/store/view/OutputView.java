package store.view;

import store.dto.ProductDTO;
import store.dto.ReceiptDTO;

import java.util.List;

public class OutputView {
    public void displayWelcomeAndProducts(List<ProductDTO> productsDTO) {
        StringBuilder productsOutputFormat = new StringBuilder();
        productsOutputFormat.append("안녕하세요. W편의점입니다.\n").append("현재 보유하고 있는 상품입니다.\n\n");
        for (ProductDTO productDTO : productsDTO) {
            productsOutputFormat.append("- ").append(productDTO.getName()).append(" ")
                    .append(String.format("%,d원 ", productDTO.getPrice()))
                    .append(getProductQuantityFormat(productDTO))
                    .append(productDTO.getPromotion()).append("\n");
        }
        System.out.println(productsOutputFormat);
    }

    private String getProductQuantityFormat(ProductDTO productDTO) {
        String quantityFormat = String.format("%,d개 ", productDTO.getQuantity());
        if (productDTO.getQuantity() == 0) {
            quantityFormat = "재고 없음";
        }
        return quantityFormat;
    }

    public void displayReceipt(ReceiptDTO receiptDTO) {
        System.out.println("===========W 편의점=============");
        System.out.println("상품명\t\t수량\t\t\t금액");
        receiptDTO.getOrderItemDTOS().forEach(orderItemDTO -> {
            System.out.println(orderItemDTO.getName() + "\t\t" + orderItemDTO.getQuantity() + "\t\t\t" +
                    orderItemDTO.getPrice());
        });
        System.out.println("===========증\t정=============");
        System.out.println("==============================");
        System.out.println("총구매액\t\t\t\t\t" + String.format("%,d", receiptDTO.getTotalPrice()));
        System.out.println("행사할인\t\t\t\t\t" + String.format("-%,d", receiptDTO.getDiscountForPromotion()));
        System.out.println("멤버십할인\t\t\t\t\t" + String.format("-%,d", receiptDTO.getDiscountForMembership()));
        System.out.println("내실돈\t\t\t\t\t" + String.format("%,d",receiptDTO.getMoneyForPay()));
    }

}
