package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.dto.FreeMoreItem;
import store.dto.NotApplicableItem;
import store.util.OutputWriter;
import store.util.InputReader;
import store.util.RequestStatus;

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

        if (!order.matches("^(\\[[가-힣]+-[0-9]+])(,(\\[[가-힣]+-[0-9]+]))*$")) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    private List<String> parseOrder(String order) {
        return Arrays.stream(order.replaceAll("\\[", "").replaceAll("]", "").split(",")).toList();
    }

    public String requestFreeMore(FreeMoreItem freeMoreItem) {
        displayFormat("현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)%n",
                freeMoreItem.name(), freeMoreItem.quantity());
        String answer = Console.readLine();
        validateYorN(answer);
        return answer;
    }

    public String requestNotApplicable(NotApplicableItem notApplicableItem) {
        displayFormat("현재 %s %s개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)%n",
                notApplicableItem.name(), notApplicableItem.quantity());
        String answer = Console.readLine();
        validateYorN(answer);
        return answer;
    }

    private void validateYorN(String answer) {
        if (answer.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
        }

        if (!RequestStatus.YES.getRequestValue().equals(answer) && !RequestStatus.NO.getRequestValue().equals(answer)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.");
        }
    }
}
