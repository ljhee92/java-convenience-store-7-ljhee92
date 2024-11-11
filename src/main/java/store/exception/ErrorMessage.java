package store.exception;

public enum ErrorMessage {
    FILE_READ_EXCEPTION("파일 읽기에 실패했습니다."),
    INSUFFICIENT_QUANTITY_EXCEPTION("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INVALID_INPUT_EXCEPTION("잘못된 입력입니다. 다시 입력해 주세요."),
    NOT_EXIST_PRODUCT_EXCEPTION("존재하지 않는 상품입니다. 다시 입력해 주세요."),
    INVALID_PRODUCT_FORMAT_EXCEPTION("올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.")
    ;

    private final String prefix = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return prefix + this.message;
    }
}
