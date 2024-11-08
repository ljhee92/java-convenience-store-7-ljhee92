package store;

import store.controller.StoreController;
import store.dao.ProductDAO;
import store.dao.PromotionDAO;
import store.domain.Promotion;
import store.mapper.ProductMapper;
import store.mapper.PromotionMapper;
import store.service.OrderService;
import store.service.ProductService;
import store.service.PromotionService;
import store.util.RetryHandler;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RetryHandler retryHandler = new RetryHandler();
        ProductMapper productMapper = new ProductMapper();
        ProductDAO productDAO = new ProductDAO(productMapper);
        ProductService productService = new ProductService(productDAO);
        PromotionMapper promotionMapper = new PromotionMapper();
        PromotionDAO promotionDAO = new PromotionDAO(promotionMapper);
        PromotionService promotionService = new PromotionService(promotionDAO);
        OrderService orderService = new OrderService();

        StoreController storeController = new StoreController(inputView, outputView, retryHandler,
                productService, orderService, promotionService);
        storeController.run();
    }
}
