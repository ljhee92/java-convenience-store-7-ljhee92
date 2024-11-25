package store.service;

import store.domain.Inventory;
import store.domain.Product;
import store.domain.Products;
import store.dto.ProductResponse;
import store.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getProductsForPurchase() {
        Products originalProducts = Products.from(getAllProducts());
        Products products = Products.from(new ArrayList<>());
        for (Product product : originalProducts) {
            products.add(product);
            if (originalProducts.onlyHasPromotionProduct(product)) {
                Product generalProduct = Product.of(product.getName(), product.getPrice());
                products.add(generalProduct);
            }
        }
        return products;
    }
}
