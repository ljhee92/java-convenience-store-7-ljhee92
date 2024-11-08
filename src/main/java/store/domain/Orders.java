package store.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Orders implements Iterable<Order> {
    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = new ArrayList<>(orders);
    }

    public void processOrder(Promotions promotions) {
        orders.forEach(order -> {
            order.checkPromotionForOrder(promotions);
            order.processOrder();
        });
    }

    @Override
    public Iterator<Order> iterator() {
        return orders.iterator();
    }

    @Override
    public void forEach(Consumer<? super Order> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Order> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orders=" + orders +
                '}';
    }
}
