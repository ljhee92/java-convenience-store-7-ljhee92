package store.service;

import store.dao.ProductDAO;
import store.domain.Product;
import store.domain.Products;
import store.dto.ProductDTO;

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

    public List<ProductDTO> getAllProductsDTO(Products products) {
        List<ProductDTO> productsDTO = new ArrayList<>();
        products.forEach(product -> addProductsDTO(productsDTO, products, product));
        return productsDTO;
    }

    private void addProductsDTO(List<ProductDTO> productsDTO, Products products, Product product) {
        ProductDTO productDTO = product.toDTO();
        productsDTO.add(productDTO);

        if (products.onlyHasOnPromotion(product)) {
            ProductDTO tempDTO = new ProductDTO(productDTO.getName(), productDTO.getPrice(), 0, "");
            productsDTO.add(tempDTO);
        }
    }
}
