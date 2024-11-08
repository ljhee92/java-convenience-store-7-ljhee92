package store.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInputReader {
    public List<String> readFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<String> contentsByLine = readContents(bufferedReader);
            closeReader(bufferedReader, fileReader);
            return contentsByLine;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> readContents(BufferedReader bufferedReader) throws IOException {
        String line = "";
        List<String> contentsByLine = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            contentsByLine.add(line);
        }
        return contentsByLine;
    }

    private void closeReader(BufferedReader bufferedReader, java.io.FileReader fileReader) throws IOException {
        bufferedReader.close();
        fileReader.close();
    }
}
