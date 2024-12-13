package store.repository;

import store.domain.Product;
import store.util.FileParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository {
    private static final String PRODUCT_FILE_PATH = "src/main/resources/products.md";
    private static final String PROMOTION_FILE_PATH = "src/main/resources/promotions.md";

    private final List<Product> products;

    public ProductRepository() {
        this.products = init();
    }

    public List<Product> findAll() {
        return products;
    }

    private List<Product> init() {
        List<Product> productsForFile = FileParser.getProducts(PRODUCT_FILE_PATH, PROMOTION_FILE_PATH);
        List<Product> products = new ArrayList<>();
        for (Product product : productsForFile) {
            products.add(product);
            if (Collections.frequency(productsForFile, product) == 1) {
                Product general = Product.empty(product.getName(), product.getPrice());
                products.add(general);
            }
            if (product.getPromotion() != null && !product.getPromotion().inPromotionPeriod()) {
                products.remove(product);
            }
        }
        return products;
    }
}
