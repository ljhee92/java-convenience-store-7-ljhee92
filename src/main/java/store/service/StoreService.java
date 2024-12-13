package store.service;

import store.domain.Product;
import store.domain.ProductItem;
import store.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class StoreService {
    private final ProductRepository productRepository;

    public StoreService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductItem> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductItem> productItems = new ArrayList<>();
        for (Product product : products) {
            ProductItem productItem = ProductItem.from(product);
            productItems.add(productItem);
        }
        return productItems;
    }
}
