package store.view;

import store.exception.InvalidInputException;
import store.exception.ProductFormatException;
import store.util.ErrorMessage;
import store.util.Parser;
import store.util.UserInputReader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputView extends UserInputReader {
    public List<Map<String, String>> requestOrder() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String inputOrder = inputMessage();
        validateOrderFormat(inputOrder);
        return parseOrder(inputOrder);
    }

    private void validateOrderFormat(String inputOrder) {
        if (inputOrder.isBlank()) {
            throw new ProductFormatException(ErrorMessage.PRODUCT_FORMAT);
        }

        if (!inputOrder.matches("^(\\[[가-힣]+-[0-9]+])(,(\\[[가-힣]+-[0-9]+]))*$")) {
            throw new ProductFormatException(ErrorMessage.PRODUCT_FORMAT);
        }
    }

    private List<Map<String, String>> parseOrder(String inputOrder) {
        String inputOrderWithoutSquareBrackets = Parser.removeSquareBrackets(inputOrder);
        List<String> inputOrders = Parser.splitByComma(inputOrderWithoutSquareBrackets);
        Map<String, String> parsedOrder = inputOrders.stream()
                .collect(Collectors.toMap(
                        order -> Parser.splitByHyphen(order).getFirst(),
                        order -> Parser.splitByHyphen(order).getLast()
                ));
        return List.of(parsedOrder);
    }

    public String requestReOrder() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String inputReOrder = inputMessage();
        validateYesOrNoFormat(inputReOrder);
        return inputReOrder;
    }

    private void validateYesOrNoFormat(String inputYesOrNo) {
        if (inputYesOrNo.isBlank()) {
            throw new InvalidInputException(ErrorMessage.INVALID_INPUT);
        }

        if (!"N".equals(inputYesOrNo) && !"Y".equals(inputYesOrNo)) {
            throw new InvalidInputException(ErrorMessage.INVALID_INPUT);
        }
    }
}
