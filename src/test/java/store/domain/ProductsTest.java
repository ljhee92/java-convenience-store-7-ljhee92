package store.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 객체 리스트 테스트")
public class ProductsTest {
    @ParameterizedTest
    @CsvSource(value = {"콜라 : true", "환타 : false", "사이다 : true"}, delimiter = ':')
    @DisplayName("입력한 상품명이 존재하는 상품인지 검증")
    void hasProduct(String inputProductName, boolean expected) {
        Product coke = new Product("콜라", 1000, 10, "탄산2+1");
        Product cider = new Product("사이다", 1000, 8, "탄산2+1");
        Products products = new Products(List.of(coke, cider));

        assertThat(products.contains(inputProductName)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"콜라 : 5", "사이다 : 8", "콜라 : 20"}, delimiter = ':')
    @DisplayName("입력한 수량이 일반 재고 + 프로모션 재고 수량 이내인지 검증")
    void withinQuantity(String inputProductName, int inputQuantity) {
        Product cokeWithPromotion = new Product("콜라", 1000, 10, "탄산2+1");
        Product coke = new Product("콜라", 1000, 10, "미진행");
        Product cider = new Product("사이다", 1000, 8, "탄산2+1");
        Products products = new Products(List.of(cokeWithPromotion, coke, cider));

        assertThat(products.availablePurchase(inputProductName, inputQuantity)).isEqualTo(true);
    }
}
