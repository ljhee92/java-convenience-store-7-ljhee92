package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("입고 관리 테스트")
public class ProductManagerTest {
    @Test
    @DisplayName("읽어 들인 상품 목록이 상품으로 등록되는지 확인")
    void registerProducts() {
        FileInputReader fileInputReader = new FileInputReader();
        ProductManager productManager = new ProductManager(fileInputReader);
        assertDoesNotThrow(() -> {
            productManager.registerProducts();
        });
    }
}
