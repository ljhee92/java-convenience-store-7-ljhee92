package store.exception;

public class FileReadException extends RuntimeException {
    public FileReadException(String message, Exception e) {
        super(ErrorMessage.FILE_READ_EXCEPTION.getMessage() + " (잘못된 파일 경로 : " + message + ")", e);
    }
}
