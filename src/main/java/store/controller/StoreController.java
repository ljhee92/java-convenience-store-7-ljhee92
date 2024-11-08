package store.controller;

import store.domain.Orders;
import store.domain.Products;
import store.domain.Promotions;
import store.domain.Receipt;
import store.dto.ProductDTO;
import store.service.OrderService;
import store.service.ProductService;
import store.service.PromotionService;
import store.util.RetryHandler;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;
import java.util.Map;

public class StoreController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RetryHandler retryHandler;
    private final ProductService productService;
    private final OrderService orderService;
    private final PromotionService promotionService;

    public StoreController(InputView inputView, OutputView outputView, RetryHandler retryHandler,
                           ProductService productService, OrderService orderService, PromotionService promotionService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.retryHandler = retryHandler;
        this.productService = productService;
        this.orderService = orderService;
        this.promotionService = promotionService;
    }

    public void run() {
        Products products = productService.getAllProducts();
        Promotions promotions = promotionService.getAllPromotions();

        do {
            order(products, promotions);
        } while (retryHandler.repeat(() -> inputView.requestReOrder().equals("Y")));
    }

    private void order(Products products, Promotions promotions) {
        List<ProductDTO> productsDTO = productService.getAllProductsDTO(products);
        outputView.displayWelcomeAndProducts(productsDTO);

        Orders orders = retryHandler.repeat(() -> takeOrders(products));
        orders.processOrder(promotions);

        Receipt receipt = new Receipt(orders);
        outputView.displayReceipt(receipt.toReceiptDTO());
    }

    private Orders takeOrders(Products products) {
        List<Map<String, String>> inputOrders = inputView.requestOrder();
        return new Orders(orderService.takeOrders(inputOrders, products));
    }
}
