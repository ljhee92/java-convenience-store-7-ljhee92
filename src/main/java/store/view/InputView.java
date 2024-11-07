package store.view;

import store.exception.ProductFormatException;
import store.util.ErrorMessage;
import store.util.Parser;
import store.util.UserInputReader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputView extends UserInputReader {
    public List<Map<String, String>> requestOrder() {
        String inputOrder = inputMessage();
        validateOrderFormat(inputOrder);
        return parseOrder(inputOrder);
    }

    private void validateOrderFormat(String inputOrder) {
        if (inputOrder.isBlank()) {
            throw new ProductFormatException(ErrorMessage.PRODUCT_FORMAT);
        }

        if (!inputOrder.matches("^(\\[[가-힣]+-[0-9]+])*,*(\\[[가-힣]+-[0-9]+])$")) {
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
}
