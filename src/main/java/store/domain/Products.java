package store.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Products implements Iterable<Product> {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public void add(Product product) {
        products.add(product);
    }

    public boolean onlyHasOnPromotion(Product product) {
        return Collections.frequency(products, product) == 1 && product.onPromotion();
    }

    public boolean contains(String productName) {
        return products.stream().anyMatch(product -> product.exist(productName));
    }

    public boolean isAvailablePurchase(String productName, int orderQuantity) {
        int totalQuantity = products.stream()
                .filter(product -> product.exist(productName))
                .mapToInt(Product::getStock)
                .sum();
        return totalQuantity >= orderQuantity;
    }

    public Products getProductsByName(String productName) {
        return new Products(products.stream()
                .filter(product -> product.exist(productName))
                .toList());
    }

    public Product getGeneralProduct() {
        for (Product product : products) {
            if (!product.onPromotion()) {
                return product;
            }
        }
        return null;
    }

    public Product getOnPromotionProduct() {
        for (Product product : products) {
            if (product.onPromotion()) {
                return product;
            }
        }
        return null;
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

    @Override
    public String toString() {
        return "Products{" +
                "products=" + products +
                '}';
    }
}
