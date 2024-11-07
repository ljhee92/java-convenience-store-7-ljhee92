package store;

import store.controller.StoreController;
import store.dao.ProductDAO;
import store.mapper.ProductMapper;
import store.service.ProductService;
import store.view.InputView;
import store.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ProductMapper productMapper = new ProductMapper();
        ProductDAO productDAO = new ProductDAO(productMapper);
        ProductService productService = new ProductService(productDAO);
        StoreController storeController = new StoreController(inputView, outputView, productService);
        storeController.run();
    }
}
