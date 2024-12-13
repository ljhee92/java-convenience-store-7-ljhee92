package store.config;

import store.controller.StoreController;
import store.repository.ProductRepository;
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

    private StoreService storeService() {
        return new StoreService(productRepository());
    }
}
