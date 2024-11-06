package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 객체 테스트")
public class ProductTest {
    @Test
    @DisplayName("상품 객체 생성 테스트")
    void createProduct() {
        assertThat(new Product("콜라", 1000, 10, "탄산2+1")).isInstanceOf(Product.class);
    }
}
