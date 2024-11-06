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
            promotion = "미진행";
        }
        this.promotion = promotion;
    }

    public boolean exist(String name) {
        return this.name.equals(name);
    }

    public int getQuantity(String name) {
        if (this.exist(name)) {
            return this.quantity;
        }
        return 0;
    }
}
