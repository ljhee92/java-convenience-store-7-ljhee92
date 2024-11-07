package store.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Promotions implements Iterable<Promotion> {
    private List<Promotion> promotions;

    public Promotions(List<Promotion> promotions) {
        this.promotions = new ArrayList<>(promotions);
    }

    public void add(Promotion promotion) {
        promotions.add(promotion);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void forEach(Consumer<? super Promotion> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Promotion> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public Iterator<Promotion> iterator() {
        return promotions.iterator();
    }
}
