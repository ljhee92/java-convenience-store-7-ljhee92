package store.view;

import store.domain.ProductItem;
import store.util.OutputWriter;

import java.util.List;

public class OutputView implements OutputWriter {
    public void displayProducts(List<ProductItem> productItems) {
        displayMessageByLine("안녕하세요. W편의점입니다.");
        displayMessageByLine("현재 보유하고 있는 상품입니다.");
        displayNewLine();
        for (ProductItem productItem : productItems) {
            displayFormat("- %s %,.0f원 %s %s", productItem.name(), productItem.price(),
                    convertToQuantityFormat(productItem.quantity()), productItem.promotionName());
        }
        displayNewLine();
    }

    private String convertToQuantityFormat(int quantity) {
        if (quantity == 0) {
            return "재고 없음";
        }
        return quantity + "개";
    }
}
