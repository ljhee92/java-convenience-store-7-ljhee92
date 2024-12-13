package store.controller;

import store.domain.OrderItem;
import store.domain.ProductItem;
import store.service.StoreService;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;

public class StoreController {
    private final InputView inputView;
    private final OutputView outputView;
    private final StoreService storeService;

    public StoreController(InputView inputView, OutputView outputView, StoreService storeService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.storeService = storeService;
    }

    public void run() {
        List<ProductItem> productItems = storeService.getAllProducts();
        outputView.displayProducts(productItems);

        List<OrderItem> orderItems = inputView.requestOrder();
    }
}
