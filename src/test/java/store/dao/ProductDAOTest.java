package store.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.mapper.ProductMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("products 데이터 접근 테스트")
public class ProductDAOTest {
    private ProductDAO productDAO;

    @BeforeEach
    void setUp() {
        ProductMapper productMapper = new ProductMapper();
        productDAO = new ProductDAO(productMapper);
    }

    @Test
    @DisplayName("파일에서 상품 목록 전체를 조회하는지 확인")
    void selectAllProducts() {
        assertAll(
                () -> assertThat(productDAO.selectAllProducts()).isNotNull(),
                () -> assertThat(productDAO.selectAllProducts()).isNotEmpty()
        );
    }
}
