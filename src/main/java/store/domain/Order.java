package store.domain;

public class Order {
    private final String name;
    private final int quantity;

    private Order(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static Order of(String name, int quantity) {
        return new Order(name, quantity);
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
                '}';
    }
}
