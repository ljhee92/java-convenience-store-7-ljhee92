package store.domain;

import java.util.*;
import java.util.function.Consumer;

public class Products implements Iterable<Product> {
    private List<Product> products;

    public Products(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public void add(Product product) {
        products.add(product);
    }

    public boolean onlyHasInProgressPromotion(Product product) {
        return Collections.frequency(products, product) == 1 && product.inProgressPromotion(product.getName());
    }

    public boolean contains(String productName) {
        return products.stream().anyMatch(product -> product.exist(productName));
    }

    public boolean availablePurchase(String productName, int quantity) {
        int totalQuantity = products.stream()
                .filter(product -> product.exist(productName))
                .mapToInt(product -> product.getQuantity(productName))
                .sum();
        return totalQuantity >= quantity;
    }

    @Override
    public void forEach(Consumer<? super Product> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Product> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
