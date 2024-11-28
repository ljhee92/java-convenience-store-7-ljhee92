package store.domain.order;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Orders implements Iterable<Order> {
    private final List<Order> orders;

    private Orders(List<Order> orders) {
        this.orders = new ArrayList<>(orders);
    }

    public static Orders from(List<Order> orders) {
        return new Orders(orders);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orders=" + orders +
                '}';
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
}
