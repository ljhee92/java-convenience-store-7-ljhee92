package store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductManager {
    private static final String PRODUCT_FILE_PATH = "src/main/resources/products.md";
    private final FileInputReader fileInputReader;

    public ProductManager(FileInputReader fileInputReader) {
        this.fileInputReader = fileInputReader;
    }

    public List<Product> registerProducts() {
        String productsFile = fileInputReader.readFile(PRODUCT_FILE_PATH);
        List<String> productsForFile = new ArrayList<>(Arrays.stream(productsFile.split("\\n")).toList());
        productsForFile.removeFirst();

        List<Product> products = new ArrayList<>();
        for (String line : productsForFile) {
            String name = Parser.splitByComma(line).get(0);
            int price = Integer.parseInt(Parser.splitByComma(line).get(1));
            int quantity = Integer.parseInt(Parser.splitByComma(line).get(2));
            String promotion = Parser.splitByComma(line).get(3);

            Product product = new Product(name, price, quantity, promotion);
            products.add(product);
        }
        return products;
    }
}
