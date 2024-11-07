package store.domain;

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
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
        }
    }

    private void validateQuantity(Products products, String name, int quantity) {
        if (!products.availablePurchase(name, quantity)) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
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
