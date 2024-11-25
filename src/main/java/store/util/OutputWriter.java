package store.util;

public interface OutputWriter {
    default void displayMessage(String message) {
        System.out.println(message);
    }

    default void displayFormat(String format, Object ... args) {
        System.out.printf(format, args);
    }

    default void displayNewLine() {
        System.out.println();
    }
}
