package store.controller;

import store.domain.Orders;
import store.service.StoreService;
import store.dto.ProductResponse;
import store.service.OrderService;
import store.util.RetryHandler;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;

public class StoreController {
    private final InputView inputView;
    private final OutputView outputView;
    private final StoreService storeService;
    private final OrderService orderService;

    public StoreController(InputView inputView, OutputView outputView,
                           StoreService storeService, OrderService orderService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.storeService = storeService;
        this.orderService = orderService;
    }

    public void run() {
        displayProducts();
        RetryHandler.repeat(this::requestOrder);
    }

    private void displayProducts() {
        List<ProductResponse> productResponses = storeService.getProductResponses();
        outputView.displayProductsForPurchase(productResponses);
    }

    private void requestOrder() {
        List<String> inputOrders = inputView.requestOrder();
        Orders orders = orderService.createOrders(inputOrders);
    }
}
