package store.domain;

import store.dto.OrderItemDTO;

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

    public List<OrderItemDTO> reduceStock() {
        List<OrderItemDTO> orderItemsDTO = new ArrayList<>();
        orders.forEach(order -> {
            order.reduceStock();
            orderItemsDTO.add(order.toDTO());
        });
        return orderItemsDTO;
    }

    public void add(Order order) {
        orders.add(order);
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
