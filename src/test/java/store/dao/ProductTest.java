package store.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import store.domain.Product;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("상품 객체 테스트")
public class ProductTest {
    @Test
    @DisplayName("상품 객체 생성 테스트")
    void createProduct() {
        assertThat(new Product("콜라", 1000, 10, "탄산2+1")).isInstanceOf(Product.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"콜라 : true", "환타 : false", "사이다 : false"}, delimiter = ':')
    @DisplayName("입력한 상품명이 존재하는 상품인지 검증")
    void contains(String inputProductName, boolean expected) {
        Product product = new Product("콜라", 1000, 10, "탄산2+1");
        assertThat(product.exist(inputProductName)).isEqualTo(expected);
    }

    @Test
    @DisplayName("입력한 상품의 재고 개수 검증")
    void getAllQuantity() {
        String inputProductName = "콜라";
        Product product = new Product("콜라", 1000, 10, "탄산2+1");
        assertThat(product.getQuantity(inputProductName)).isEqualTo(10);
    }
}
