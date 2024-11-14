package store.config;

import store.controller.StoreController;
import store.dao.ProductDAO;
import store.dao.PromotionDAO;
import store.mapper.ProductMapper;
import store.mapper.PromotionMapper;
import store.service.OrderService;
import store.service.ProductService;
import store.service.PromotionService;
import store.util.RetryHandler;
import store.view.InputView;
import store.view.OutputView;

public class AppConfig {
    public StoreController storeController() {
        return new StoreController(inputView(), outputView(), retryHandler(),
                productService(), promotionService(), orderService());
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public RetryHandler retryHandler() {
        return new RetryHandler();
    }

    public ProductMapper productMapper() {
        return new ProductMapper();
    }

    public ProductDAO productDAO() {
        return new ProductDAO(productMapper());
    }

    public ProductService productService() {
        return new ProductService(productDAO());
    }

    public PromotionMapper promotionMapper() {
        return new PromotionMapper();
    }

    public PromotionDAO promotionDAO() {
        return new PromotionDAO(promotionMapper());
    }

    public PromotionService promotionService() {
        return new PromotionService(promotionDAO());
    }

    public OrderService orderService() {
        return new OrderService();
    }
}
