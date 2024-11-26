package store.domain;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private final List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = new ArrayList<>(orders);
    }

    public static Orders of(List<Order> orders) {
        return new Orders(orders);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orders=" + orders +
                '}';
    }
}
