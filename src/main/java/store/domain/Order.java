package store.domain;

import store.exception.InsufficientQuantityException;
import store.exception.NotExistProductException;
import store.util.ErrorMessage;

public class Order {
    private final String name;
    private final int quantity;
    private final Products products;
    private Promotion promotion;

    public Order(String name, int quantity, Products products) {
        validateProductName(products, name);
        validateQuantity(products, name, quantity);

        this.name = name;
        this.quantity = quantity;
        this.products = products.getProductsByName(name);
    }

    private void validateProductName(Products products, String name) {
        if (!products.contains(name)) {
            throw new NotExistProductException(ErrorMessage.NOT_EXIST_PRODUCT);
        }
    }

    private void validateQuantity(Products products, String name, int quantity) {
        if (!products.isAvailablePurchase(name, quantity)) {
            throw new InsufficientQuantityException(ErrorMessage.INSUFFICIENT_QUANTITY);
        }
    }

    public void checkPromotionForOrder(Promotions promotions) {
        for (Product product : products) {
            String promotionName = product.getPromotion();
            if (!"null".equals(promotionName)) {
                this.promotion = promotions.getPromotionByName(promotionName);
            }
        }
    }

    public void processOrder() {
        int remainingQuantity = quantity;

        if (promotion != null && promotion.inPromotionPeriod()) {
            Product onPromotionProduct = products.getOnPromotionProduct();
            int onPromotionStock = onPromotionProduct.getStock();

            int buy = promotion.getBuy();
            int free = promotion.getFree();

            if (remainingQuantity < buy + free) {
                System.out.println("현재 오렌지주스은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)");
            }

            remainingQuantity = getRemainingQuantity(remainingQuantity, onPromotionProduct, onPromotionStock);
        }
        if (products.getGeneralProduct() != null) {
            Product generalProduct = products.getGeneralProduct();
            int generalStock = generalProduct.getStock();

            if (remainingQuantity > 0) {
                remainingQuantity = getRemainingQuantity(remainingQuantity, generalProduct, generalStock);
            }
        }
        System.out.println(products);
    }

    private int getRemainingQuantity(int remainingQuantity, Product product, int stock) {
        if (stock > 0 && stock >= quantity) {
            product.reduceStock(quantity);
            remainingQuantity -= quantity;
        }

        if (stock > 0 && stock < quantity) {
            product.reduceStock(stock);
            remainingQuantity -= stock;
        }
        return remainingQuantity;
    }

    public int getPurchaseAmount() {
        Product generalProduct = products.getGeneralProduct();
        int generalProductPrice = generalProduct.getPrice();
        return generalProductPrice * quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
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
