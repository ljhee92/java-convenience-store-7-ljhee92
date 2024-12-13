package store.util;

import store.domain.Product;
import store.domain.Promotion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    private static final Pattern PRODUCT_PATTERN
            = Pattern.compile("^([가-힣]+),([0-9]+),([0-9]+),([a-zA-Z가-힣0-9+]+)$");
    private static final Pattern PROMOTION_PATTERN
            = Pattern.compile("^([a-zA-Z가-힣0-9+]+),([0-9]+),([0-9]+),([0-9-]+),([0-9-]+)$");
    private static final int PRODUCT_NAME = 1;
    private static final int PRICE = 2;
    private static final int QUANTITY = 3;
    private static final int PRODUCT_PROMOTION = 4;
    private static final int PROMOTION_NAME = 1;
    private static final int BUY = 2;
    private static final int GET = 3;
    private static final int START_DATE = 4;
    private static final int END_DATE = 5;

    private FileParser() {}

    public static List<Product> getProducts(String productFilePath, String promotionFilePath) {
        List<Product> products = new ArrayList<>();
        Map<String, Promotion> promotions = new HashMap<>();

        List<String> productsForFile = ResourceReader.readFile(productFilePath);
        List<String> promotionsForFile = ResourceReader.readFile(promotionFilePath);

        for (String line : promotionsForFile) {
            Matcher matcher = PROMOTION_PATTERN.matcher(line);
            if (matcher.matches()) {
                Promotion promotion = Promotion.of(
                        matcher.group(PROMOTION_NAME),
                        Parser.parseToInt(matcher.group(BUY)),
                        Parser.parseToInt(matcher.group(GET)),
                        LocalDate.parse(matcher.group(START_DATE)),
                        LocalDate.parse(matcher.group(END_DATE))
                );
                promotions.put(matcher.group(PROMOTION_NAME), promotion);
            }
        }

        for (String line : productsForFile) {
            Matcher matcher = PRODUCT_PATTERN.matcher(line);
            if (matcher.matches()) {
                String name = matcher.group(PRODUCT_NAME);
                BigDecimal price = BigDecimal.valueOf(
                        Parser.parseToInt(matcher.group(PRICE))
                );
                int quantity = Parser.parseToInt(matcher.group(QUANTITY));
                String promotion = matcher.group(PRODUCT_PROMOTION);
                if (!"null".equals(promotion)) {
                    Product product = Product.of(name, price, quantity, promotions.get(promotion));
                    products.add(product);
                } else {
                    Product product = Product.of(name, price, quantity);
                    products.add(product);
                }
            }
        }

        return products;
    }
}
