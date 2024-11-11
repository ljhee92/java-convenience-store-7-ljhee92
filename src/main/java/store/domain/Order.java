package store.domain;

import store.dto.OrderItemDTO;
import store.exception.InsufficientQuantityException;
import store.exception.NotExistProductException;

import java.util.List;

public class Order {
    private final String name;
    private final int quantity;
    private final Products products;
    private final Promotion promotion;
    private List<Integer> purchasedQuantities;
    private int freeMoreQuantity;

    public Order(String name, int quantity, Products products, Promotions promotions) {
        validateProductName(products, name);
        validateQuantity(products, name, quantity);

        this.name = name;
        this.quantity = quantity;
        this.products = products.getProductsByName(name);
        this.promotion = promotions.getPromotionByName(getPromotionName());
    }

    private void validateProductName(Products products, String name) {
        if (!products.contains(name)) {
            throw new NotExistProductException(name);
        }
    }

    private void validateQuantity(Products products, String name, int quantity) {
        if (!products.isAvailablePurchase(name, quantity)) {
            throw new InsufficientQuantityException(String.format("%s, %d", name, quantity));
        }
    }

    public String getPromotionName() {
        return products.getPromotionName();
    }

    public void reduceStock() {
        this.purchasedQuantities = products.reduceStock(quantity);

        if (!onlyGeneralPromotion()) {
            this.freeMoreQuantity = getFreeMore(purchasedQuantities.getFirst());
        }
    }

    public boolean onlyGeneralPromotion() {
        return promotion == null;
    }

    private int getFreeMore(int orderQuantity) {
        if (orderQuantity % (promotion.getBuy() + promotion.getFree()) == 0) {
            return 0;
        }
        return promotion.getFree();
    }

    public OrderItemDTO toDTO() {
        int totalQuantity = quantity;
        int pricePerProduct = products.getPricePerProduct();
        int orderedPromotionQuantity = purchasedQuantities.getFirst();
        int orderedNotPromotionQuantity = purchasedQuantities.getLast();
        int notApplicablePromotionQuantity = 0;
        int freeQuantity = 0;
        if (promotion != null) {
            if (promotion.getBuy() == 2) {
                notApplicablePromotionQuantity
                        = orderedPromotionQuantity % (promotion.getBuy() + promotion.getFree())
                        + orderedNotPromotionQuantity;

                if (orderedPromotionQuantity == 2) {
                    notApplicablePromotionQuantity
                            = orderedNotPromotionQuantity;
                }
            }

            if (promotion.getBuy() == 1) {
                notApplicablePromotionQuantity = orderedPromotionQuantity;
                totalQuantity += orderedPromotionQuantity;
            }

            freeQuantity = orderedPromotionQuantity / (promotion.getBuy() + promotion.getFree());

            if (products.size() == 1) {
                freeQuantity =1;
            }
        }

        return new OrderItemDTO(name, totalQuantity, pricePerProduct, orderedPromotionQuantity,
                orderedNotPromotionQuantity, freeMoreQuantity, notApplicablePromotionQuantity, freeQuantity);
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", products=" + products +
                ", promotion=" + promotion +
                '}';
    }
}
