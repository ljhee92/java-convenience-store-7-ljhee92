package store.util;

import camp.nextstep.edu.missionutils.Console;

public interface InputReader {
    default String readUserInput() {
        return Console.readLine();
    }
}
