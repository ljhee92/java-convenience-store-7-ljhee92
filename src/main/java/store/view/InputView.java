package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.util.OutputWriter;
import store.util.InputReader;

import java.util.Arrays;
import java.util.List;

public class InputView implements InputReader, OutputWriter {
    public List<String> requestOrder() {
        displayMessage("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String order = Console.readLine();
        validateOrder(order);
        return parseOrder(order);
    }

    private void validateOrder(String order) {
        if (order.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }

        if (!order.matches("^(\\[[가-힣]+-[0-9]+])+,*(\\[[가-힣]+-[0-9]+])*$")) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private List<String> parseOrder(String order) {
        return Arrays.stream(order.split(",")).toList();
    }
}
