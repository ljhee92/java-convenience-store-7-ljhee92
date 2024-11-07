package store.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 데이터 매핑 테스트")
public class ProductMapperTest {
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = new ProductMapper();
    }

    @ParameterizedTest
    @ValueSource(strings = {"컵라면,1700,1,MD추천상품", "정식도시락,6400,8,null"})
    @DisplayName("읽어 들인 상품 정보가 상품 객체로 변환되는지 확인")
    void mapToProduct(String line) {
        assertThat(productMapper.mapToProduct(line)).isInstanceOf(Product.class);
    }
}
