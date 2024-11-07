package store.controller;

import store.dto.ProductDTO;
import store.domain.Order;
import store.domain.Products;
import store.service.ProductService;
import store.view.InputView;
import store.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class StoreController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ProductService productService;

    public StoreController(InputView inputView, OutputView outputView, ProductService productService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.productService = productService;
    }

    public void run() {
        List<ProductDTO> productsDTO = productService.searchAllProducts();
        outputView.displayWelcomeAndProducts(productsDTO);

        Products products = productService.getAllProducts();
        List<Order> orders = repeat(() -> takeOrder(products));
    }

    private List<Order> takeOrder(Products products) {
        outputView.requestOrder();
        List<Map<String, String>> inputOrders = inputView.requestOrder();
        List<Order> orders = new ArrayList<>(inputOrders.size());

        inputOrders.forEach(inputOrder -> {
            for (String name : inputOrder.keySet()) {
                Order order = new Order(products, name, Integer.parseInt(inputOrder.get(name)));
                orders.add(order);
            }
        });
        return orders;
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.displayErrorMessage(e.getMessage());
            return repeat(supplier);
        }
    }
}
