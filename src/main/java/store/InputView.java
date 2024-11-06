package store;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InputView extends UserInputReader {
    public Map<String, String> requestPurchaseProduct() {
        String inputPurchaseProduct = inputMessage();

        if (inputPurchaseProduct.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (!inputPurchaseProduct.matches("^(\\[[가-힣]+-[0-9]+])*,*(\\[[가-힣]+-[0-9]+])$")) {
            throw new IllegalArgumentException();
        }

        String inputPurchaseProductWithoutSquareBrackets
                = Parser.removeSquareBrackets(inputPurchaseProduct);
        List<String> inputPurchaseProducts
                = Parser.splitByComma(inputPurchaseProductWithoutSquareBrackets);

        return inputPurchaseProducts.stream()
                .collect(Collectors.toMap(
                        product -> Parser.splitByHyphen(product).getFirst(),
                        product -> Parser.splitByHyphen(product).getLast()
                ));
    }
}
