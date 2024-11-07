package store.mapper;

import store.util.Parser;
import store.domain.Promotion;

import java.time.LocalDate;

public class PromotionMapper {
    public Promotion mapToPromotion(String line) {
        String name = Parser.splitByComma(line).get(0);
        int buy = Parser.stringToInt(Parser.splitByComma(line).get(1));
        int sell = Parser.stringToInt(Parser.splitByComma(line).get(2));
        LocalDate startDate = LocalDate.parse(Parser.splitByComma(line).get(3));
        LocalDate endDate = LocalDate.parse(Parser.splitByComma(line).get(4));

        return new Promotion(name, buy, sell, startDate, endDate);
    }
}
