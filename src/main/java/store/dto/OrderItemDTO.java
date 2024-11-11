package store.dto;

public class OrderItemDTO {
    private final String name;
    private final int totalQuantity;
    private final double pricePerProduct;
    private int orderedPromotionQuantity;
    private final int orderedNotPromotionQuantity;
    private final int freeMoreQuantity;
    private final int notApplicablePromotionQuantity;
    private final int freeQuantity;

    public OrderItemDTO(String name, int totalQuantity, double pricePerProduct,
                        int orderedPromotionQuantity, int orderedNotPromotionQuantity, int freeMoreQuantity,
                        int notApplicablePromotionQuantity, int freeQuantity) {
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.pricePerProduct = pricePerProduct;
        this.orderedPromotionQuantity = orderedPromotionQuantity;
        this.orderedNotPromotionQuantity = orderedNotPromotionQuantity;
        this.freeMoreQuantity = freeMoreQuantity;
        this.notApplicablePromotionQuantity = notApplicablePromotionQuantity;
        this.freeQuantity = freeQuantity;
    }

    public String getName() {
        return name;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public double getPricePerProduct() {
        return pricePerProduct;
    }

    public int getOrderedPromotionQuantity() {
        return orderedPromotionQuantity;
    }

    public int getOrderedNotPromotionQuantity() {
        return orderedNotPromotionQuantity;
    }

    public int getFreeMoreQuantity() {
        return freeMoreQuantity;
    }

    public int getNotApplicablePromotionQuantity() {
        return notApplicablePromotionQuantity;
    }

    public int getFreeQuantity() {
        return freeQuantity;
    }

    public void setOrderedPromotionQuantity(int orderedPromotionQuantity) {
        this.orderedPromotionQuantity = orderedPromotionQuantity;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "name='" + name + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", pricePerProduct=" + pricePerProduct +
                ", orderedPromotionQuantity=" + orderedPromotionQuantity +
                ", orderedNotPromotionQuantity=" + orderedNotPromotionQuantity +
                ", freeMoreQuantity=" + freeMoreQuantity +
                ", notApplicablePromotionQuantity=" + notApplicablePromotionQuantity +
                ", freeQuantity=" + freeQuantity +
                '}';
    }
}
