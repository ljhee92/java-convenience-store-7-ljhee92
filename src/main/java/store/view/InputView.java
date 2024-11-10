package store.view;

import store.dto.OrderItemDTO;
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

    public boolean acceptFreeMore(OrderItemDTO orderItemDTO) {
        System.out.println();
        System.out.println("현재 " + orderItemDTO.getName() + "은(는) " +
                (orderItemDTO.getFreeMoreQuantity() + orderItemDTO.getOrderedNotPromotionQuantity()) +
                "개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
        String inputFreeMore = inputMessage();
        validateYesOrNoFormat(inputFreeMore);
        return "Y".equals(inputFreeMore);
    }

    public boolean acceptApplicabilityForPromotion(OrderItemDTO orderItemDTO) {
        System.out.println();
        System.out.println("현재 " + orderItemDTO.getName() + "은(는) " +
                (orderItemDTO.getNotApplicablePromotionQuantity()) +
                "개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)");
        String applicabilityForPromotion = inputMessage();
        validateYesOrNoFormat(applicabilityForPromotion);
        return "Y".equals(applicabilityForPromotion);
    }

    public boolean acceptApplicabilityForMembership() {
        System.out.println();
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        String applicabilityForMembership = inputMessage();
        validateYesOrNoFormat(applicabilityForMembership);
        return "Y".equals(applicabilityForMembership);
    }

    public boolean acceptReOrder() {
        System.out.println();
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String inputReOrder = inputMessage();
        validateYesOrNoFormat(inputReOrder);
        return "Y".equals(inputReOrder);
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
