package store.repository;

import store.domain.promotion.Promotion;
import store.util.ResourceReader;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PromotionRepository {
    private final String PROMOTION_PATTERN = "^([a-zA-Z가-힣0-9+]+),([0-9]+),([0-9]+),([0-9-]+),([0-9-]+)$";
    private final Pattern pattern = Pattern.compile(PROMOTION_PATTERN);
    private final int PROMOTION_NAME = 1;
    private final int PROMOTION_BUY = 2;
    private final int PROMOTION_FREE = 3;
    private final int PROMOTION_START_DATE = 4;
    private final int PROMOTION_END_DATE = 5;

    public List<Promotion> findAll() {
        List<String> contentsByLine = ResourceReader.readFile("src/main/resources/promotions.md");

        return contentsByLine.stream().map(pattern::matcher)
                .filter(Matcher::matches)
                .map(matcher -> Promotion.of(
                        matcher.group(PROMOTION_NAME),
                        Integer.parseInt(matcher.group(PROMOTION_BUY)), Integer.parseInt(matcher.group(PROMOTION_FREE)),
                        LocalDate.parse(matcher.group(PROMOTION_START_DATE)),
                        LocalDate.parse(matcher.group(PROMOTION_END_DATE))
                )).toList();
    }
}
