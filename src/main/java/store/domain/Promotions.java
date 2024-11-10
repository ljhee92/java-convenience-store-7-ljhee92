package store.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Promotions implements Iterable<Promotion> {
    private final List<Promotion> promotions;

    public Promotions(List<Promotion> promotions) {
        this.promotions = new ArrayList<>(promotions);
    }

    public Promotion getPromotionByName(String promotionName) {
        return promotions.stream().filter(promotion -> promotion.exist(promotionName))
                .filter(Promotion::inPromotionPeriod)
                .findFirst().orElse(null);
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

    @Override
    public String toString() {
        return "Promotions{" +
                "promotions=" + promotions +
                '}';
    }
}
