package store.controller;

import store.domain.Order;
import store.domain.Orders;
import store.domain.Purchase;
import store.domain.Purchases;
import store.domain.Store;
import store.dto.FreeMoreItem;
import store.service.StoreService;
import store.dto.ProductResponse;
import store.util.RequestStatus;
import store.util.RetryHandler;
import store.view.InputView;
import store.view.OutputView;

import java.util.ArrayList;
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
        Store store = storeService.openStore();
        displayProducts(store);
        Orders orders = RetryHandler.repeat(() -> getOrders(store));
        Purchases purchases = store.createPurchases(orders);
        RetryHandler.repeat(() -> requestFreeMore(store, purchases));
    }

    private void displayProducts(Store store) {
        List<ProductResponse> productResponses = store.getProductResponses();
        outputView.displayProductsForPurchase(productResponses);
    }

    private Orders getOrders(Store store) {
        List<String> inputOrders = inputView.requestOrder();
        List<Order> orders = new ArrayList<>();
        inputOrders.forEach(inputOrder -> {
            String name = inputOrder.split("-")[0];
            int quantity = Integer.parseInt(inputOrder.split("-")[1]);
            Order order = getValidOrder(store, name, quantity);
            orders.add(order);
        });
        return Orders.from(orders);
    }

    private Order getValidOrder(Store store, String name, int quantity) {
        store.validProductName(name);
        store.enoughQuantity(name, quantity);
        return Order.of(name, quantity);
    }

    private void requestFreeMore(Store store, Purchases purchases) {
        String answer = "";
        List<FreeMoreItem> freeMoreItems = store.getFreeMoreItems(purchases);
        for (FreeMoreItem freeMoreItem : freeMoreItems) {
            if (freeMoreItem.quantity() != 0) {
                answer = inputView.requestFreeMore(freeMoreItem);
            }
        }

        if (RequestStatus.YES.getRequestValue().equals(answer)) {
            for (FreeMoreItem freeMoreItem : freeMoreItems) {
                purchases.addFreeMoreItems(freeMoreItem.quantity());
            }
        }
    }
}
