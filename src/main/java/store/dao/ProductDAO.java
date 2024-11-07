package store.dao;

import store.domain.Product;
import store.mapper.ProductMapper;
import store.util.FileInputReader;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String PRODUCT_FILE_PATH = "src/main/resources/products.md";

    private final ProductMapper productMapper;

    public ProductDAO(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public List<Product> selectAllProducts() {
        List<String> productsForFileByLine = load();
        List<Product> products = new ArrayList<>();
        for (String line : productsForFileByLine) {
            Product product = productMapper.mapToProduct(line);
            products.add(product);
        }
        return products;
    }

    private List<String> load() {
        FileInputReader fileInputReader = new FileInputReader();
        List<String> contentsByLine = fileInputReader.readFile(PRODUCT_FILE_PATH);
        contentsByLine.removeFirst();
        return contentsByLine;
    }
}
