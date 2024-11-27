package store.config;

import store.controller.StoreController;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;

public class AppConfig {
    public StoreController storeController() {
        return new StoreController(inputView(), outputView(), storeService());
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

    private PromotionRepository promotionRepository() {
        return new PromotionRepository();
    }

    private StoreService storeService() {
        return new StoreService(productRepository(), promotionRepository());
    }
}
