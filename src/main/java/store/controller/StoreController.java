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
    private static final int ZERO = 0;
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
        ReceiptDTO receiptDTO = processOrder();
        outputView.displayReceipt(receiptDTO);

        repeatUntilExit(products, promotions);
    }

    private void welcomeGreetingAndTakeOrder(Products products, Promotions promotions) {
        List<ProductDTO> productsDTO = productService.getAllProductsDTO(products);
        outputView.displayWelcomeAndProducts(productsDTO);
        retryHandler.repeat(() -> takeOrders(products, promotions));
    }

    private void takeOrders(Products products, Promotions promotions) {
        List<List<String>> inputOrders = inputView.requestOrder();
        orderService.createOrders(inputOrders, products, promotions);
    }

    private ReceiptDTO processOrder() {
        List<OrderItemDTO> orderItemsDTO = orderService.processOrders();

        orderItemsDTO.forEach(orderItemDTO -> {
            retryHandler.repeat(() -> checkFreeMore(orderItemDTO));
            retryHandler.repeat(() -> checkNotApplicablePromotion(orderItemDTO));
        });

        return retryHandler.repeat(() -> checkMembershipDiscount(orderItemsDTO));
    }

    private void checkFreeMore(OrderItemDTO orderItemDTO) {
        if (orderItemDTO.getFreeMoreQuantity() != ZERO) {
            if (inputView.acceptFreeMore(orderItemDTO)) {
                productService.reduceStockForFree(orderItemDTO.getName(), orderItemDTO.getFreeMoreQuantity());
            }
        }
    }

    private void checkNotApplicablePromotion(OrderItemDTO orderItemDTO) {
        if (orderItemDTO.getNotApplicablePromotionQuantity() != ZERO) {
            if (!inputView.acceptApplicabilityForPromotion(orderItemDTO)) {
                productService.resetStockForNotApplicablePromotion(orderItemDTO);
            }

            orderItemDTO.setOrderedPromotionQuantity(orderItemDTO.getOrderedPromotionQuantity() +
                    orderItemDTO.getFreeMoreQuantity());
        }
    }

    private ReceiptDTO checkMembershipDiscount(List<OrderItemDTO> orderItemsDTO) {
        ReceiptDTO receiptDTO = orderService.createReceipt(orderItemsDTO);
        if (!inputView.acceptApplicabilityForMembership()) {
            orderService.disableDiscountForMembership(receiptDTO);
        }

        return receiptDTO;
    }

    private void repeatUntilExit(Products products, Promotions promotions) {
        retryHandler.repeat(() -> {
            while (inputView.acceptReOrder()) {
                retryHandler.repeat(() -> welcomeGreetingAndTakeOrder(products, promotions));
                ReceiptDTO receiptDTO = processOrder();
                outputView.displayReceipt(receiptDTO);
            }
        });
    }
}
