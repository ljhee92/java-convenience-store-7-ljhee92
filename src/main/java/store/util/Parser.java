package store.util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private Parser () {}

    public static String removeSquareBrackets(String input) {
        return input.replaceAll("\\[", "").replaceAll("]", "");
    }

    public static List<String> splitByComma(String input) {
        return Arrays.stream(input.split(",")).toList();
    }

    public static List<String> splitByHyphen(String input) {
        return Arrays.stream(input.split("-")).toList();
    }

    public static int stringToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
