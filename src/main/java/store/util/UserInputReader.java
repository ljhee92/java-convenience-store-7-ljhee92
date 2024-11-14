package store.util;

import camp.nextstep.edu.missionutils.Console;

public interface UserInputReader {
    default String inputMessage() {
        return Console.readLine();
    }
}
