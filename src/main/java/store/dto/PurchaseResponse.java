package store.dto;

import java.math.BigDecimal;

public record PurchaseResponse (String name, int total, int general, int promotion, int free, BigDecimal pricePerUnit) {
}
