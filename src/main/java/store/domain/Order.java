package store.domain;

public class Order {
    private final String name;
    private final int quantity;

    private Order(String name, int quantity, Products productsOfInventory) {
        this.name = name;
        this.quantity = quantity;
        validateProductName(productsOfInventory);
        validateQuantity(productsOfInventory);
    }

    public static Order of(String name, int quantity, Products productsOfInventory) {
        return new Order(name, quantity, productsOfInventory);
    }

    private void validateProductName(Products productsOfInventory) {
        if (!productsOfInventory.hasProduct(name)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.");
        }
    }

    private void validateQuantity(Products productsOfInventory) {
        if (productsOfInventory.notEnoughStock(name, quantity)) {
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
