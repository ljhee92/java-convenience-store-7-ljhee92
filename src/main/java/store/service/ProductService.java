package store.service;

import store.dto.ProductDTO;
import store.dao.ProductDAO;
import store.domain.Product;
import store.domain.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Products getAllProducts() {
        return new Products(productDAO.selectAllProducts());
    }

    public List<ProductDTO> searchAllProducts() {
        Products products = new Products(productDAO.selectAllProducts());
        List<ProductDTO> productsDTO = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = product.toDTO();
            productsDTO.add(productDTO);
            if (products.onlyHasInProgressPromotion(product)) {
                ProductDTO tempDTO = new ProductDTO(productDTO.getName(), productDTO.getPrice(), 0, "");
                productsDTO.add(tempDTO);
            }
        }
        return productsDTO;
    }
}
