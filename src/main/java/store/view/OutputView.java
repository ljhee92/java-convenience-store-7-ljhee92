package store.view;

import store.dto.ProductDTO;
import store.util.UserOutputWriter;

import java.util.List;

public class OutputView extends UserOutputWriter {
    public void displayWelcomeAndProducts(List<ProductDTO> productsDTO) {
        StringBuilder productsOutputFormat = new StringBuilder();
        productsOutputFormat.append("안녕하세요. W편의점입니다.\n").append("현재 보유하고 있는 상품입니다.\n\n");
        for (ProductDTO productDTO : productsDTO) {
            productsOutputFormat.append("- ").append(productDTO.getName()).append(" ")
                    .append(String.format("%,d원 ", productDTO.getPrice()))
                    .append(getProductQuantityFormat(productDTO))
                    .append(productDTO.getPromotion()).append("\n");
        }
        displayMessage(productsOutputFormat.toString());
    }

    private String getProductQuantityFormat(ProductDTO productDTO) {
        String quantityFormat = String.format("%,d개 ", productDTO.getQuantity());
        if (productDTO.getQuantity() == 0) {
            quantityFormat = "재고 없음";
        }
        return quantityFormat;
    }

    public void requestOrder() {
        displayMessage("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }

    public void displayErrorMessage(String errorMessage) {
        displayMessage(errorMessage);
    }
}
