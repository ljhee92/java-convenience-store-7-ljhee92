package store.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("파싱 테스트")
public class ParserTest {
    @Test
    @DisplayName("문자에서 대괄호([])를 없애는지 검증")
    void removeSquareBrackets() {
        String input = "[콜라-10]";
        assertThat(Parser.removeSquareBrackets(input)).isEqualTo("콜라-10");
    }

    @Test
    @DisplayName("문자를 쉼표(,)를 기준으로 구분하는지 검증")
    void splitByComma() {
        String input = "[콜라-10],[사이다-3]";
        assertThat(Parser.splitByComma(input)).isEqualTo(List.of("[콜라-10]", "[사이다-3]"));
    }

    @Test
    @DisplayName("문자를 하이픈(-)을 기준으로 구분하는지 검증")
    void splitByHyphen() {
        String input = "콜라-10";
        assertThat(Parser.splitByHyphen(input)).isEqualTo(List.of("콜라", "10"));
    }
}
