package store.service;

import store.domain.Inventory;
import store.domain.Product;
import store.dto.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {
    private final Inventory inventory;

    public InventoryService(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<ProductResponse> getProductResponses() {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : inventory.getProducts()) {
            ProductResponse productResponse = ProductResponse.from(product);
            productResponses.add(productResponse);
        }
        return productResponses;
    }
}
