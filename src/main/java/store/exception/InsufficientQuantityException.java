package store.exception;

public class InsufficientQuantityException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.";

    public InsufficientQuantityException(String message) {
        super(ERROR_MESSAGE + "(재고 수량 초과 : " + message + ")");
    }
}
