package store.domain;

import store.domain.order.Order;
import store.domain.order.Orders;
import store.domain.product.Product;
import store.domain.product.Products;
import store.domain.promotion.Promotion;
import store.domain.promotion.Promotions;
import store.domain.purchase.Purchase;
import store.domain.purchase.Purchases;
import store.dto.FreeMoreItem;
import store.dto.NotApplicableItem;
import store.dto.ProductResponse;

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
            String promotionName = products.getPromotionName(name);
            if (promotionName != null && promotions.inPromotionPeriod(promotionName)) {
                Promotion promotion = promotions.getPromotion(promotionName);
                int promotionStock = products.getProductQuantity(name);
                int promotionQuantity = Math.min(quantity,
                        promotionStock-promotion.getNotApplicable(quantity));
                purchase = Purchase.ofOnPromotion(name, quantity, Math.max(0, quantity-promotionStock),
                        promotionQuantity, promotion.getFree(promotionQuantity), products.getProductPrice(name));
            }
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
            if (promotionName != null && promotions.inPromotionPeriod(promotionName)) {
                Promotion promotion = promotions.getPromotion(promotionName);
                FreeMoreItem freeMoreItem = new FreeMoreItem(name, calculateFreeMore(name, quantity, promotion));
                freeMoreItems.add(freeMoreItem);
            }
        }
        return freeMoreItems;
    }

    private int calculateFreeMore(String name, int quantity, Promotion promotion) {
        int freeMore = 0;
        if (products.enoughPromotionStock(name, quantity)) {
            freeMore = promotion.getFreeMore(quantity);
        }
        return freeMore;
    }

    public List<NotApplicableItem> getNotApplicableItems(Purchases purchases) {
        List<NotApplicableItem> notApplicableItems = new ArrayList<>();
        for (Purchase purchase : purchases) {
            String name = purchase.getName();
            int quantity = purchase.getBuyQuantity();
            String promotionName = products.getPromotionName(name);
            if (promotionName != null && promotions.inPromotionPeriod(promotionName)) {
                Promotion promotion = promotions.getPromotion(promotionName);
                int promotionStock = products.getProductQuantity(name);
                NotApplicableItem notApplicableItem = new NotApplicableItem(name,
                        calculateNotApplicableOfGeneral(name, quantity, promotionStock),
                        calculateNotApplicableOfPromotion(name, quantity, promotionStock, promotion));
                notApplicableItems.add(notApplicableItem);
            }
        }
        return notApplicableItems;
    }

    private int calculateNotApplicableOfGeneral(String name, int quantity, int promotionStock) {
        int notApplicableQuantity = 0;
        if (!products.enoughPromotionStock(name, quantity)) {
            notApplicableQuantity = quantity - promotionStock;
        }
        return notApplicableQuantity;
    }

    private int calculateNotApplicableOfPromotion(String name, int quantity, int promotionStock, Promotion promotion) {
        int notApplicableQuantity = 0;
        if (!products.enoughPromotionStock(name, quantity)) {
            notApplicableQuantity = promotion.getNotApplicable(promotionStock);
        }
        return notApplicableQuantity;
    }

    public Store sell(Purchases purchases) {
        for (Purchase purchase : purchases) {
            String name = purchase.getName();
            products.minusGeneralQuantity(name, purchase.getGeneralQuantity());
            if (purchase.isOnPromotion()) {
                products.minusPromotionQuantity(name, purchase.getBuyQuantity() - purchase.getGeneralQuantity());
            }
        }
        return Store.of(products, promotions);
    }
}
