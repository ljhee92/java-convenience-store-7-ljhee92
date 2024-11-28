package store.dto;

import java.math.BigDecimal;
import java.util.List;

public record Receipt (List<PurchaseResponse> purchaseResponses, int totalQuantity, BigDecimal totalPrice,
                      BigDecimal discountPromotionPrice, BigDecimal discountMembershipPrice,
                      BigDecimal payPrice) {
}
