package store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("주문 객체 테스트")
public class OrderTest {
    private Products products;

    @BeforeEach
    void setUp() {
        Product coke = new Product("콜라", 1000, 10, "탄산2+1");
        Product cider = new Product("사이다", 1000, 8, "탄산2+1");
        products = new Products(List.of(coke, cider));
    }

    @Test
    @DisplayName("주문 객체 생성 테스트")
    void createOrder() {
        assertThat(new Order(products, "콜라", 10)).isInstanceOf(Order.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"환타 : 1", "꽃게랑 : 10", "빼빼로 : 5"}, delimiter = ':')
    @DisplayName("주문한 상품명에 존재하지 않는 상품을 포함하고 있다면 예외처리 검증")
    void hasProductException(String inputName, int inputQuantity) {
        assertThatThrownBy(() -> {
            new Order(products, inputName, inputQuantity);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"콜라 : 11", "사이다 : 9"}, delimiter = ':')
    @DisplayName("주문한 수량이 재고 수량을 초과하면 예외처리 검증")
    void withinQuantityException(String inputName, int inputQuantity) {
        assertThatThrownBy(() -> {
            new Order(products, inputName, inputQuantity);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
