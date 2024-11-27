package store.domain;

import store.dto.FreeMoreItem;
import store.dto.ProductResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final Products products;
    private final Promotions promotions;

    private Store(Products products, Promotions promotions) {
        this.products = products;
        this.promotions = promotions;
    }

    public static Store of(Products products, Promotions promotions) {
        return new Store(products, promotions);
    }

    public List<ProductResponse> getProductResponses() {
        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            ProductResponse productResponse = ProductResponse.from(product);
            productResponses.add(productResponse);
        }
        return productResponses;
    }

    public void validProductName(String name) {
        if (!products.hasProduct(name)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
        }
    }

    public void enoughQuantity(String name, int quantity) {
        if (products.notEnoughStock(name, quantity)) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }

    public Purchases createPurchases(Orders orders) {
        List<Purchase> purchases = new ArrayList<>();
        for (Order order : orders) {
            String name = order.getName();
            int quantity = order.getQuantity();

            Purchase purchase = Purchase.of(name, quantity, products.getProductPrice(name));
            purchases.add(purchase);
        }
        return Purchases.from(purchases);
    }

    public List<FreeMoreItem> getFreeMoreItems(Purchases purchases) {
        List<FreeMoreItem> freeMoreItems = new ArrayList<>();
        for (Purchase purchase : purchases) {
            String name = purchase.getName();
            int quantity = purchase.getBuyQuantity();

            String promotionName = products.getPromotionName(name);
            Promotion promotion = promotions.getPromotion(promotionName);
            FreeMoreItem freeMoreItem = new FreeMoreItem(name, calculateFreeQuantity(name, quantity, promotion));
            freeMoreItems.add(freeMoreItem);
        }
        return freeMoreItems;
    }

    private int calculateFreeQuantity(String name, int quantity, Promotion promotion) {
        int freeQuantity = 0;
        if (products.enoughPromotionStock(name, quantity)) {
            freeQuantity = promotion.getFreeMore(quantity);
        }
        return freeQuantity;
    }
}
