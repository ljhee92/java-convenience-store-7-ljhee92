package store.controller;

import store.domain.Products;
import store.domain.Promotions;
import store.dto.OrderItemDTO;
import store.dto.ProductDTO;
import store.dto.ReceiptDTO;
import store.service.OrderService;
import store.service.ProductService;
import store.service.PromotionService;
import store.util.RetryHandler;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;

public class StoreController {
    private final InputView inputView;
    private final OutputView outputView;
    private final RetryHandler retryHandler;
    private final ProductService productService;
    private final OrderService orderService;
    private final PromotionService promotionService;

    public StoreController(InputView inputView, OutputView outputView, RetryHandler retryHandler,
                           ProductService productService, PromotionService promotionService, OrderService orderService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.retryHandler = retryHandler;
        this.productService = productService;
        this.promotionService = promotionService;
        this.orderService = orderService;
    }

    public void run() {
        Products products = productService.getAllProducts();
        Promotions promotions = promotionService.getAllPromotions();

        welcomeGreetingAndTakeOrder(products, promotions);
        progressDiscount();

        retryHandler.repeat(() -> {
            while (inputView.acceptReOrder()) {
                retryHandler.repeat(() -> welcomeGreetingAndTakeOrder(products, promotions));
                progressDiscount();
            }
        });
    }

    private void welcomeGreetingAndTakeOrder(Products products, Promotions promotions) {
        List<ProductDTO> productsDTO = productService.getAllProductsDTO(products);
        outputView.displayWelcomeAndProducts(productsDTO);
        retryHandler.repeat(() -> takeOrders(products, promotions));
    }

    private void takeOrders(Products products, Promotions promotions) {
        List<List<String>> inputOrders = inputView.requestOrder();
        orderService.takeOrders(inputOrders, products, promotions);
    }

    private void progressDiscount() {
        List<OrderItemDTO> orderItemsDTO = orderService.progressOrders();

        for (OrderItemDTO orderItemDTO : orderItemsDTO) {
            checkFreeMore(orderItemDTO);
            checkNotApplicablePromotion(orderItemDTO);
        }

        if (!inputView.acceptApplicabilityForMembership()) {
            ReceiptDTO receiptDTO = orderService.createReceipt(orderItemsDTO);
            outputView.displayReceipt(receiptDTO);
        }
    }

    private void checkFreeMore(OrderItemDTO orderItemDTO) {
        if (orderItemDTO.getFreeMoreQuantity() >= orderItemDTO.getTotalQuantity()) {
            if (inputView.acceptFreeMore(orderItemDTO)) {
                productService.reduceStockForFree(orderItemDTO.getName(), orderItemDTO.getFreeMoreQuantity());
            }
        }
    }

    private void checkNotApplicablePromotion(OrderItemDTO orderItemDTO) {
        if (orderItemDTO.getFreeMoreQuantity() != 0
                && orderItemDTO.getTotalQuantity() > orderItemDTO.getOrderedPromotionQuantity()) {
            if (!inputView.acceptApplicabilityForPromotion(orderItemDTO)) {
                productService.resetStockForNotApplicablePromotion(orderItemDTO.getName(),
                        orderItemDTO.getOrderedPromotionQuantity(),
                        orderItemDTO.getOrderedNotPromotionQuantity());
            }
        }
    }
}
