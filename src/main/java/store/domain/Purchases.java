package store.domain;

import store.dto.PurchaseResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Purchases implements Iterable<Purchase> {
    private final List<Purchase> purchases;

    private Purchases(List<Purchase> purchases) {
        this.purchases = new ArrayList<>(purchases);
    }

    public static Purchases from(List<Purchase> purchases) {
        return new Purchases(purchases);
    }

    public void addFreeMoreItems(int freeMore) {
        purchases.forEach(purchase -> {
            purchase.addFreeMore(freeMore);
        });
    }

    public void setNotApplicable(int notApplicable) {
        purchases.forEach(purchase -> {
            purchase.setNotApplicable(notApplicable);
        });
    }

    public void minusNotApplicable(int notApplicableOfGeneral, int notApplicableOfPromotion) {
        purchases.forEach(purchase -> {
            purchase.minusNotApplicable(notApplicableOfGeneral, notApplicableOfPromotion);
        });
    }

    public BigDecimal getTotalPrice() {
        return purchases.stream().map(Purchase::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getApplyPromotionPrice() {
        return purchases.stream().map(Purchase::getApplyPromotionPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getFreePromotionPrice() {
        return purchases.stream().map(Purchase::getFreePromotionPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<PurchaseResponse> toResponse() {
        return purchases.stream().map(Purchase::toResponse).collect(Collectors.toList());
    }

    public int getTotalQuantity() {
        return purchases.stream().mapToInt(Purchase::getBuyQuantity).sum();
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

    @Override
    public String toString() {
        return "Purchases{" +
                "purchases=" + purchases +
                '}';
    }
}
