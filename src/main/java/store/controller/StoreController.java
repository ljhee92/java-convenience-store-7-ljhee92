package store.controller;

import store.domain.Calculator;
import store.domain.Membership;
import store.domain.Order;
import store.domain.Orders;
import store.domain.Purchases;
import store.domain.Store;
import store.dto.FreeMoreItem;
import store.dto.NotApplicableItem;
import store.service.StoreService;
import store.dto.ProductResponse;
import store.util.RequestStatus;
import store.util.RetryHandler;
import store.view.InputView;
import store.view.OutputView;

import java.lang.reflect.Member;
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
        RetryHandler.repeat(() -> requestNotApplicable(store, purchases));
        RetryHandler.repeat(() -> requestApplyMembership(purchases));
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
        addFreeMore(purchases, answer, freeMoreItems);
    }

    private void addFreeMore(Purchases purchases, String answer, List<FreeMoreItem> freeMoreItems) {
        if (RequestStatus.YES.getRequestValue().equals(answer)) {
            for (FreeMoreItem freeMoreItem : freeMoreItems) {
                purchases.addFreeMoreItems(freeMoreItem.quantity());
            }
        }
    }

    private void requestNotApplicable(Store store, Purchases purchases) {
        String answer = "";
        List<NotApplicableItem> notApplicableItems = store.getNotApplicableItems(purchases);
        for (NotApplicableItem notApplicableItem : notApplicableItems) {
            if (notApplicableItem.quantity() != 0) {
                answer = inputView.requestNotApplicable(notApplicableItem);
            }
        }
        minusNotApplicable(purchases, answer, notApplicableItems);
    }

    private void minusNotApplicable(Purchases purchases, String answer, List<NotApplicableItem> notApplicableItems) {
        if (RequestStatus.NO.getRequestValue().equals(answer)) {
            for (NotApplicableItem notApplicableItem : notApplicableItems) {
                purchases.updateNotApplicableItems(notApplicableItem.quantity());
            }
        }
    }

    private void requestApplyMembership(Purchases purchases) {
        String answer = inputView.requestApplyMembership();
        Calculator calculator = Calculator.of(purchases, new Membership());

        if (RequestStatus.YES.getRequestValue().equals(answer)) {
            calculator.calculateMembershipDiscountAmount();
        }
    }
}
