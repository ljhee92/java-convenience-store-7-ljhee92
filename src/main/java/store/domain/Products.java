package store.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Products implements Iterable<Product> {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = new ArrayList<>(products);
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

    public void add(Product product) {
        products.add(product);
    }

    public Products getProductsByName(String productName) {
        return new Products(products.stream()
                .filter(product -> product.exist(productName))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    public String getPromotionName() {
        return products.stream().filter(Product::onPromotion)
                .map(Product::getPromotion).collect(Collectors.joining(""));
    }

    public List<Integer> reduceStock(int orderQuantity) {
        List<Integer> purchasedQuantities = new ArrayList<>();

        int remainingQuantity = reducePromotionStock(purchasedQuantities, orderQuantity);
        reduceGeneralStock(purchasedQuantities, orderQuantity, remainingQuantity);

        adjustQuantitiesWhenOnlyHasGeneral(purchasedQuantities, remainingQuantity);
        adjustQuantitiesWhenOnlHasPromotion(purchasedQuantities, remainingQuantity);

        return purchasedQuantities;
    }

    private int reducePromotionStock(List<Integer> purchasedQuantities, int orderQuantity) {
        int remainingQuantity = products.getFirst().reduceStock(orderQuantity);
        if (remainingQuantity < 0) {
            purchasedQuantities.add(-remainingQuantity);
            purchasedQuantities.add(0);
        }
        return remainingQuantity;
    }

    private void reduceGeneralStock(List<Integer> purchasedQuantities, int orderQuantity, int remainingQuantity) {
        if (remainingQuantity >= 0) {
            purchasedQuantities.add(orderQuantity - remainingQuantity);
            remainingQuantity = products.getLast().reduceStock(remainingQuantity);
            purchasedQuantities.add(-remainingQuantity);
        }
    }

    private void adjustQuantitiesWhenOnlyHasGeneral(List<Integer> purchasedQuantities, int remainingQuantity) {
        if (products.size() == 1) {
            purchasedQuantities.removeFirst();
            purchasedQuantities.add(-remainingQuantity);
        }
    }

    private void adjustQuantitiesWhenOnlHasPromotion(List<Integer> purchasedQuantities, int remainingQuantity) {
        if (onlyHasOnPromotion(products.getFirst())) {
            purchasedQuantities.clear();
            purchasedQuantities.add(-remainingQuantity);
            purchasedQuantities.add(0);
        }
    }

    public int getPricePerProduct() {
        return products.getFirst().getPrice();
    }

    public void reduceStockForFree(String productName, int freeMoreQuantity) {
        for (Product product : products) {
            if (product.exist(productName) && product.onPromotion()) {
                product.reduceStockForFree(freeMoreQuantity);
            }
        }
    }

    public void resetStockForNotApplicablePromotion(String productName, int orderedPromotionQuantity,
                                                    int orderedNotPromotionQuantity) {
        for (Product product : products) {
            if (product.exist(productName) && product.onPromotion()) {
                product.resetStockForNotPurchase(orderedPromotionQuantity);
            }

            if (product.exist(productName) && !product.onPromotion()) {
                product.resetStockForNotPurchase(orderedNotPromotionQuantity);
            }
        }
    }

    public int size() {
        return products.size();
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
