package store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInputReader {
    public String openFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            StringBuilder contents = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                contents.append(line).append("\n");
            }

            bufferedReader.close();
            fileReader.close();

            return contents.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
