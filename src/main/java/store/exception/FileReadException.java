package store.exception;

public class FileReadException extends RuntimeException {
    private static final String ERROR_MESSAGE = "[ERROR] 파일 읽기에 실패했습니다.";

    public FileReadException(String message, Exception e) {
        super(ERROR_MESSAGE + "(잘못된 파일 경로 : " + message + ")", e);
    }
}
