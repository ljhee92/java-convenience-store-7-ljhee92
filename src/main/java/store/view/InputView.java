package store.view;

import store.domain.OrderItem;
import store.util.ErrorMessage;
import store.util.InputReader;
import store.util.OutputWriter;
import store.util.Parser;

import java.util.ArrayList;
import java.util.List;

public class InputView implements InputReader, OutputWriter {
    public List<OrderItem> requestOrder() {
        displayMessageByLine("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = inputMessage();
        validateOrder(input);
        List<OrderItem> orderItems = new ArrayList<>();
        input = input.replace("\\[", "").replace("]", "");
        List<String> items = Parser.parseToList(input);
        for (String item : items) {
            OrderItem orderItem = new OrderItem(item.split(",")[0], Parser.parseToInt(item.split(",")[1]));
            orderItems.add(orderItem);
        }
        return orderItems;
    }

    private void validateOrder(String input) {
        if (!input.matches("^(\\[[가-힣]+-[0-9]+])(,(\\[[가-힣]+-[0-9]+]))*$")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT_FORMAT_EXCEPTION.getMessage());
        }
    }
}
