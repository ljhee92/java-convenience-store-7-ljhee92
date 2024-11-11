package store.domain;

import store.dto.OrderItemDTO;
import store.exception.InsufficientQuantityException;
import store.exception.NotExistProductException;

import java.util.List;

public class Order {
    private static final int ZERO = 0;
    private static final int INITIAL_VALUE = 0;
    private final String name;
    private int quantity;
    private final Products products;
    private final Promotion promotion;
    private List<Integer> orderedQuantities;
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
            throw new InsufficientQuantityException(String.format("%s-%d", name, quantity));
        }
    }

    public String getPromotionName() {
        return this.products.getPromotionName();
    }

    public void reduceStock() {
        this.orderedQuantities = products.reduceStock(quantity);
    }

    private boolean orderHasPromotion() {
        return this.promotion != null;
    }

    private int calculateFreeMore(int orderedPromotionQuantity) {
        if (orderedPromotionQuantity % (this.promotion.getBuy() + this.promotion.getFree()) == ZERO ||
                this.products.isEmptyPromotionStock(orderedPromotionQuantity + this.promotion.getFree())) {
            return ZERO;
        }

        return this.promotion.getFree();
    }

    private int calculateNotApplicablePromotionQuantity(int orderedPromotionQuantity, int orderedGeneralQuantity) {
        return (orderedPromotionQuantity + freeMoreQuantity)
                % (this.promotion.getBuy() + this.promotion.getFree())
                + orderedGeneralQuantity;
    }

    private int calculateFreeQuantity(int orderedPromotionQuantity) {
        int freeQuantity = orderedPromotionQuantity / (this.promotion.getBuy() + this.promotion.getFree());

        if (this.products.isEmptyGeneralStock()) {
            freeQuantity = (orderedPromotionQuantity + this.promotion.getFree())
                    / (this.promotion.getBuy() + this.promotion.getFree());
        }
        return freeQuantity;
    }

    public OrderItemDTO toDTO() {
        int orderedPromotionQuantity = orderedQuantities.getFirst();
        int orderedGeneralQuantity = orderedQuantities.getLast();
        int notApplicablePromotionQuantity = INITIAL_VALUE;
        int freeQuantity = INITIAL_VALUE;

        if (orderHasPromotion()) {
            this.freeMoreQuantity = calculateFreeMore(orderedPromotionQuantity);
            notApplicablePromotionQuantity
                    = calculateNotApplicablePromotionQuantity(orderedPromotionQuantity, orderedGeneralQuantity);
            freeQuantity = calculateFreeQuantity(orderedPromotionQuantity);
        }

        return new OrderItemDTO(this.name, this.quantity, products.getPricePerProduct(), orderedPromotionQuantity,
                orderedGeneralQuantity, this.freeMoreQuantity, notApplicablePromotionQuantity, freeQuantity);
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
