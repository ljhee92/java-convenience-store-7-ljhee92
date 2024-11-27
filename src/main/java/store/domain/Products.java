package store.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Products implements Iterable<Product> {
    private final List<Product> products;

    private Products(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public static Products from(List<Product> products) {
        return new Products(products);
    }

    public boolean onlyHasPromotionProduct(Product product) {
        return Collections.frequency(this.products, product) == 1 && product.onPromotion();
    }

    public void add(Product product) {
        this.products.add(product);
    }

    public boolean hasProduct(String productName) {
        return products.stream().anyMatch(product -> productName.equals(product.getName()));
    }

    public boolean notEnoughStock(String productName, int quantity) {
        return products.stream().filter(product -> productName.equals(product.getName()))
                .mapToInt(Product::getQuantity).sum() < quantity;
    }

    public boolean enoughPromotionStock(String productName, int quantity) {
        return products.stream().filter(product -> productName.equals(product.getName()))
                .filter(Product::onPromotion)
                .mapToInt(Product::getQuantity).sum() > quantity;
    }

    public String getPromotionName(String productName) {
        return products.stream().filter(product -> productName.equals(product.getName()))
                .filter(Product::onPromotion)
                .map(Product::getPromotion).findFirst().orElse(null);
    }

    public BigDecimal getProductPrice(String productName) {
        return products.stream().filter(product -> productName.equals(product.getName()))
                .map(Product::getPrice).findFirst().orElse(null);
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
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
    public String toString() {
        return "Products{" +
                "products=" + products +
                '}';
    }
}
