package store.service;

import store.domain.Product;
import store.domain.Products;
import store.domain.Promotion;
import store.dto.ProductResponse;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;

import java.util.ArrayList;
import java.util.List;

public class StoreService {
    private final ProductRepository productRepository;
    private final PromotionRepository promotionRepository;

    public StoreService(ProductRepository productRepository, PromotionRepository promotionRepository) {
        this.productRepository = productRepository;
        this.promotionRepository = promotionRepository;
    }

    private List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    private List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Products getProductsOfInventory() {
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

    public List<ProductResponse> getProductResponses() {
        Products productsOfInventory = getProductsOfInventory();
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : productsOfInventory) {
            ProductResponse productResponse = ProductResponse.from(product);
            productResponses.add(productResponse);
        }
        return productResponses;
    }
}
