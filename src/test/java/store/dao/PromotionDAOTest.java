package store.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.mapper.PromotionMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("promotions 데이터 접근 테스트")
public class PromotionDAOTest {
    private PromotionDAO promotionDAO;

    @BeforeEach
    void setUp() {
        PromotionMapper promotionMapper = new PromotionMapper();
        promotionDAO = new PromotionDAO(promotionMapper);
    }

    @Test
    @DisplayName("파일에서 행사 목록 전체를 조회하는지 확인")
    void selectAllProducts() {
        assertAll(
                () -> assertThat(promotionDAO.selectAllPromotions()).isNotNull(),
                () -> assertThat(promotionDAO.selectAllPromotions()).isNotEmpty()
        );
    }
}
