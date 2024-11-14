package store.exception;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException(String message) {
        super(ErrorMessage.INVALID_INPUT_EXCEPTION.getMessage() + " (잘못된 입력 : " + message + ")");
    }

    public InvalidInputException(String message, Exception e) {
        super(ErrorMessage.INVALID_INPUT_EXCEPTION.getMessage() + " (잘못된 입력 : " + message + ")", e);
    }
}
