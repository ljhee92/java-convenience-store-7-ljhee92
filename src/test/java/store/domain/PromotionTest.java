package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("프로모션 객체 테스트")
public class PromotionTest {
    @Test
    @DisplayName("프로모션 객체 생성 테스트")
    void createPromotion() {
        assertThat(new Promotion("탄산2+1", 2, 1,
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31)))
                .isInstanceOf(Promotion.class);
    }
}
