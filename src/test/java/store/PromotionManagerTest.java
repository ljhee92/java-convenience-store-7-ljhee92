package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("프로모션 관리 테스트")
public class PromotionManagerTest {
    @Test
    @DisplayName("읽어 들인 상품이 프로모션으로 등록되는지 확인")
    void registerPromotion() {
        FileInputReader fileInputReader = new FileInputReader();
        PromotionManager promotionManager = new PromotionManager(fileInputReader);
        assertDoesNotThrow(() -> {
            promotionManager.registerPromotions();
        });
    }
}
