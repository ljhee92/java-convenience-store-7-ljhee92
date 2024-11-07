package store.domain;

import store.exception.InsufficientQuantityException;
import store.exception.NotExistProductException;
import store.util.ErrorMessage;

public class Order {
    private final String name;
    private final int quantity;

    public Order(Products products, String name, int quantity) {
        validateProductName(products, name);
        validateQuantity(products, name, quantity);

        this.name = name;
        this.quantity = quantity;
    }

    private void validateProductName(Products products, String name) {
        if (!products.contains(name)) {
            throw new NotExistProductException(ErrorMessage.NOT_EXIST_PRODUCT);
        }
    }

    private void validateQuantity(Products products, String name, int quantity) {
        if (!products.availablePurchase(name, quantity)) {
            throw new InsufficientQuantityException(ErrorMessage.INSUFFICIENT_QUANTITY);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
