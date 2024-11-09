package store.view;

import store.exception.InvalidInputException;
import store.exception.ProductFormatException;
import store.util.Parser;
import store.util.UserInputReader;

import java.util.ArrayList;
import java.util.List;

public class InputView extends UserInputReader {
    public List<List<String>> requestOrder() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String inputOrder = inputMessage();
        validateOrderFormat(inputOrder);
        return parseOrder(inputOrder);
    }

    private void validateOrderFormat(String inputOrder) {
        if (inputOrder.isBlank()) {
            throw new ProductFormatException(inputOrder);
        }

        if (!inputOrder.matches("^(\\[[가-힣]+-[0-9]+])(,(\\[[가-힣]+-[0-9]+]))*$")) {
            throw new ProductFormatException(inputOrder);
        }
    }

    private List<List<String>> parseOrder(String inputOrder) {
        String inputOrderWithoutSquareBrackets = Parser.removeSquareBrackets(inputOrder);
        List<String> inputOrders = Parser.splitByComma(inputOrderWithoutSquareBrackets);
        List<List<String>> orderItems = new ArrayList<>();
        inputOrders.forEach(orderUnit -> {
            List<String> orderItem = Parser.splitByHyphen(orderUnit);
            orderItems.add(orderItem);
        });
        return orderItems;
    }

    public String requestReOrder() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String inputReOrder = inputMessage();
        validateYesOrNoFormat(inputReOrder);
        return inputReOrder;
    }

    private void validateYesOrNoFormat(String inputYesOrNo) {
        if (inputYesOrNo.isBlank()) {
            throw new InvalidInputException(inputYesOrNo);
        }

        if (!"N".equals(inputYesOrNo) && !"Y".equals(inputYesOrNo)) {
            throw new InvalidInputException(inputYesOrNo);
        }
    }
}
