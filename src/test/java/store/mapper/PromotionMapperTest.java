package store.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.domain.Promotion;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("행사 데이터 매핑 테스트")
public class PromotionMapperTest {
    private PromotionMapper promotionMapper;

    @BeforeEach
    void setUp() {
        promotionMapper = new PromotionMapper();
    }

    @ParameterizedTest
    @ValueSource(strings = {"탄산2+1,2,1,2024-01-01,2024-12-31", "반짝할인,1,1,2024-11-01,2024-11-30"})
    @DisplayName("읽어 들인 행사 정보가 프로모션 객체로 변환되는지 확인")
    void mapToPromotion(String line) {
        assertThat(promotionMapper.mapToPromotion(line)).isInstanceOf(Promotion.class);
    }
}
