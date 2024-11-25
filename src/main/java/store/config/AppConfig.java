package store.config;

import store.controller.StoreController;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.service.InventoryService;
import store.service.OrderService;
import store.service.ProductService;
import store.service.PromotionService;
import store.view.InputView;
import store.view.OutputView;

public class AppConfig {
    public StoreController storeController() {
        return new StoreController(inputView(), outputView(),
                productService(), promotionService());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }

    private ProductRepository productRepository() {
        return new ProductRepository();
    }

    private ProductService productService() {
        return new ProductService(productRepository());
    }

    private PromotionRepository promotionRepository() {
        return new PromotionRepository();
    }

    private PromotionService promotionService() {
        return new PromotionService(promotionRepository());
    }
}
