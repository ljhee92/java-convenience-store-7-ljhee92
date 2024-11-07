package store.mapper;

import store.util.Parser;
import store.domain.Product;

public class ProductMapper {
    public Product mapToProduct(String line) {
        String name = Parser.splitByComma(line).get(0);
        int price = Parser.stringToInt(Parser.splitByComma(line).get(1));
        int quantity  = Parser.stringToInt(Parser.splitByComma(line).get(2));
        String promotion = Parser.splitByComma(line).get(3);

        return new Product(name, price, quantity, promotion);
    }
}
