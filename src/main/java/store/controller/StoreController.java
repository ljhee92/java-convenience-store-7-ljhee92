package store.controller;

import store.domain.Inventory;
import store.domain.Orders;
import store.domain.Products;
import store.domain.Promotion;
import store.dto.ProductResponse;
import store.service.InventoryService;
import store.service.OrderService;
import store.service.ProductService;
import store.service.PromotionService;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;

public class StoreController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ProductService productService;
    private final PromotionService promotionService;

    public StoreController(InputView inputView, OutputView outputView,
                           ProductService productService, PromotionService promotionService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.productService = productService;
        this.promotionService = promotionService;
    }

    public void run() {
        Inventory inventory = initInventory();
        displayProducts(inventory);
    }

    private Inventory initInventory() {
        Products products = productService.getProductsForPurchase();
        List<Promotion> promotions = promotionService.getAllPromotions();
        return Inventory.of(products, promotions);
    }

    private void displayProducts(Inventory inventory) {
        List<ProductResponse> productResponses = new InventoryService(inventory).getProductResponses();
        outputView.displayProductsForPurchase(productResponses);
    }
}
