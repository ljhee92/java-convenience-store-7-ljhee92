package store.repository;

import store.domain.product.Product;
import store.util.ResourceReader;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductRepository {
    private final String PRODUCT_PATTERN = "^([가-힣]+),([0-9]+),([0-9]+),([a-zA-Z가-힣0-9+]+)$";
    private final Pattern pattern = Pattern.compile(PRODUCT_PATTERN);
    private final int PRODUCT_NAME = 1;
    private final int PRODUCT_PRICE = 2;
    private final int PRODUCT_QUANTITY = 3;
    private final int PRODUCT_PROMOTION = 4;

    public List<Product> findAll() {
        List<String> contentsByLine = ResourceReader.readFile("src/main/resources/products.md");

        return contentsByLine.stream().map(pattern::matcher)
                .filter(Matcher::matches)
                .map(matcher -> Product.of(
                        matcher.group(PRODUCT_NAME), BigDecimal.valueOf(Integer.parseInt(matcher.group(PRODUCT_PRICE))),
                        Integer.parseInt(matcher.group(PRODUCT_QUANTITY)), matcher.group(PRODUCT_PROMOTION)
                )).toList();
    }
}
