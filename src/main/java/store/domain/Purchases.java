package store.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Purchases implements Iterable<Purchase> {
    private final List<Purchase> purchases;

    private Purchases(List<Purchase> purchases) {
        this.purchases = new ArrayList<>(purchases);
    }

    public static Purchases from(List<Purchase> purchases) {
        return new Purchases(purchases);
    }

    public void addFreeMoreItems(int freeQuantity) {
        purchases.forEach(purchase -> {
            purchase.addFreeQuantity(freeQuantity);
        });
    }

    public void updateNotApplicableItems(int notApplicableQuantity) {
        purchases.forEach(purchase -> {
            purchase.updateNotApplicableQuantity(notApplicableQuantity);
        });
    }

    @Override
    public Iterator<Purchase> iterator() {
        return purchases.iterator();
    }

    @Override
    public void forEach(Consumer<? super Purchase> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Purchase> spliterator() {
        return Iterable.super.spliterator();
    }
}
