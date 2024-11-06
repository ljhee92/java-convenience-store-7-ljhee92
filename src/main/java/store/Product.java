package store;

public class Product {
    private final String name;
    private final int price;
    private int quantity;
    private final String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;

        if ("null".equals(promotion)) {
            promotion = "재고없음";
        }
        this.promotion = promotion;
    }
}
