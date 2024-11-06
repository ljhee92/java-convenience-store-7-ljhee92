package store;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PromotionManager {
    private static final String PROMOTION_FILE_PATH = "src/main/resources/promotions.md";
    private final FileInputReader fileInputReader;

    public PromotionManager(FileInputReader fileInputReader) {
        this.fileInputReader = fileInputReader;
    }

    public List<Promotion> registerPromotions() {
        String promotionsFile = fileInputReader.readFile(PROMOTION_FILE_PATH);
        List<String> productsForFile = new ArrayList<>(Arrays.stream(promotionsFile.split("\\n")).toList());
        productsForFile.removeFirst();

        List<Promotion> promotions = new ArrayList<>();
        for (String line : productsForFile) {
            String name = Parser.splitByComma(line).get(0);
            int buy = Integer.parseInt(Parser.splitByComma(line).get(1));
            int get = Integer.parseInt(Parser.splitByComma(line).get(2));
            LocalDate startDate = LocalDate.parse(Parser.splitByComma(line).get(3), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(Parser.splitByComma(line).get(4), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Promotion promotion = new Promotion(name, buy, get, startDate, endDate);
            promotions.add(promotion);
        }
        return promotions;
    }
}
