package store.view;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import store.exception.ProductFormatException;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("사용자 입력 테스트")
public class InputViewTest extends NsTest {
    private InputView inputView;

    @BeforeEach
    void setUp() {
        inputView = new InputView();
    }

    @Test
    @DisplayName("입력한 구매할 상품과 수량의 형식 검증")
    void validateFormat() {
        run("[콜라-10],[사이다-3]");
        assertThat(inputView.requestOrder())
                .isEqualTo(List.of(Map.of("콜라", "10", "사이다", "3")));
    }

    @ParameterizedTest()
    @ValueSource(strings = {"\n", " ", "[콜라-10].[사이다-3]", "[콜라.10],[사이다.3]", "'콜라-10','사이다-3'"})
    @DisplayName("입력한 구매할 상품과 수량의 형식이 올바르지 않을 때 예외처리 검증")
    void inValidFormat(String input) {
        run(input);
        assertThatThrownBy(() -> {
            inputView.requestOrder();
        }).isInstanceOf(ProductFormatException.class);
    }

    @Override
    protected void runMain() {}
}
