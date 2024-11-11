package store.service;

import store.domain.Order;
import store.domain.Orders;
import store.domain.Products;
import store.domain.Promotions;
import store.domain.Receipt;
import store.dto.OrderItemDTO;
import store.dto.ReceiptDTO;
import store.util.Parser;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static final int ZERO = 0;
    private Orders orders;
    private Receipt receipt;

    public void createOrders(List<List<String>> inputOrders, Products products, Promotions promotions) {
        orders = new Orders(new ArrayList<>());
        inputOrders.forEach(inputOrder -> {
            String name = inputOrder.getFirst();
            int quantity = Parser.stringToInt(inputOrder.getLast());

            Order order = new Order(name, quantity, products, promotions);
            orders.add(order);
        });
    }

    public List<OrderItemDTO> processOrders() {
        return orders.reduceStock();
    }

    public ReceiptDTO createReceipt(List<OrderItemDTO> orderItemsDTO) {
        receipt = new Receipt(orderItemsDTO);
        return receipt.toDTO();
    }

    public void disableDiscountForMembership(ReceiptDTO receiptDTO) {
        receiptDTO.setMoneyForPay(receiptDTO.getDiscountForMembership() + receiptDTO.getMoneyForPay());
        receiptDTO.setDiscountForMembership(ZERO);
    }

    public void processApplicablePromotionQuantity(OrderItemDTO orderItemDTO) {
        orderItemDTO.setOrderedPromotionQuantity(orderItemDTO.getOrderedPromotionQuantity() +
                orderItemDTO.getFreeMoreQuantity());
    }

    public void processNotApplicablePromotionQuantity(OrderItemDTO orderItemDTO) {
        orderItemDTO.setTotalQuantity(
                orderItemDTO.getTotalQuantity() - orderItemDTO.getNotApplicablePromotionQuantity());
    }
}
