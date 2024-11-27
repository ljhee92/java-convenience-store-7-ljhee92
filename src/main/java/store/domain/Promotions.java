package store.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Promotions implements Iterable<Promotion> {
    private final List<Promotion> promotions;

    private Promotions(List<Promotion> promotions) {
        this.promotions = new ArrayList<>(promotions);
    }

    public static Promotions from(List<Promotion> promotions) {
        return new Promotions(promotions);
    }

    public Promotion getPromotion(String promotionName) {
        return promotions.stream()
                .filter(promotion -> promotionName.equals(promotion.getName()))
                .filter(Promotion::inPromotionPeriod)
                .findFirst().orElse(null);
    }

    @Override
    public Iterator<Promotion> iterator() {
        return promotions.iterator();
    }

    @Override
    public void forEach(Consumer<? super Promotion> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Promotion> spliterator() {
        return Iterable.super.spliterator();
    }
}
