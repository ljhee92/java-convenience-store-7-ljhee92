package store.view;

import store.dto.OrderItemDTO;
import store.exception.InvalidInputException;
import store.exception.InvalidProductFormatException;
import store.util.OutputWriter;
import store.util.Parser;
import store.util.UserInputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InputView implements UserInputReader, OutputWriter {
    private final Pattern INPUT_ORDER_FORMAT = Pattern.compile("^(\\[[가-힣]+-[0-9]+])(,(\\[[가-힣]+-[0-9]+]))*$");
    private final String YES = "Y";
    private final String NO = "N";

    public List<List<String>> requestOrder() {
        displayMessage("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");

        String inputOrder = inputMessage();
        validateOrderFormat(inputOrder);

        return parseOrder(inputOrder);
    }

    private void validateOrderFormat(String inputOrder) {
        if (inputOrder.isBlank()) {
            throw new InvalidProductFormatException(inputOrder);
        }

        if (!INPUT_ORDER_FORMAT.matcher(inputOrder).matches()) {
            throw new InvalidProductFormatException(inputOrder);
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

    public boolean acceptFreeMore(OrderItemDTO orderItemDTO) {
        displayFormat("%n현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)%n", orderItemDTO.getName(),
                (orderItemDTO.getFreeMoreQuantity() + orderItemDTO.getOrderedNotPromotionQuantity()));

        String inputFreeMore = inputMessage();
        validateYesOrNoFormat(inputFreeMore);

        return YES.equals(inputFreeMore);
    }

    public boolean acceptApplicabilityForPromotion(OrderItemDTO orderItemDTO) {
        displayFormat("%n현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)%n",
                orderItemDTO.getName(), orderItemDTO.getNotApplicablePromotionQuantity());

        String applicabilityForPromotion = inputMessage();
        validateYesOrNoFormat(applicabilityForPromotion);

        return YES.equals(applicabilityForPromotion);
    }

    public boolean acceptApplicabilityForMembership() {
        displayNewLine();
        displayMessage("멤버십 할인을 받으시겠습니까? (Y/N)");

        String applicabilityForMembership = inputMessage();
        validateYesOrNoFormat(applicabilityForMembership);

        return YES.equals(applicabilityForMembership);
    }

    public boolean acceptReOrder() {
        displayNewLine();
        displayMessage("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");

        String inputReOrder = inputMessage();
        validateYesOrNoFormat(inputReOrder);

        return YES.equals(inputReOrder);
    }

    private void validateYesOrNoFormat(String inputYesOrNo) {
        if (inputYesOrNo.isBlank()) {
            throw new InvalidInputException(inputYesOrNo);
        }

        if (!NO.equals(inputYesOrNo) && !YES.equals(inputYesOrNo)) {
            throw new InvalidInputException(inputYesOrNo);
        }
    }
}
