package store.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceReader {
    private ResourceReader () {}

    public static List<String> readFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> lines = readLines(bufferedReader);
            closeReader(bufferedReader, fileReader);
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> readLines(BufferedReader bufferedReader) throws IOException {
        String line = "";
        List<String> lines = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    }

    private static void closeReader(BufferedReader bufferedReader, FileReader fileReader) throws IOException {
        bufferedReader.close();
        fileReader.close();
    }
}
