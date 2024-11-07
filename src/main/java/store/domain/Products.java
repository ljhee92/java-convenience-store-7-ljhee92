package store.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Products implements Iterable {
    private List<Product> products;

    public Products(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public void add(Product product) {
        products.add(product);
    }

    public boolean contains(String productName) {
        boolean found = false;
        for (Product product : products) {
            if (product.exist(productName)) {
                found = true;
                break;
            }
        }
        return found;
    }

    public boolean availablePurchase(String productName, int quantity) {
        boolean found = false;
        int totalQuantity = 0;

        for (Product product : products) {
            totalQuantity += product.getQuantity(productName);
            if (totalQuantity >= quantity) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return Iterable.super.spliterator();
    }
}
