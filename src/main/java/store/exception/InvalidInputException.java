package store.exception;

public class InvalidInputException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.";

    public InvalidInputException(String message) {
        super(ERROR_MESSAGE + "(잘못된 입력 : " + message + ")");
    }
}
