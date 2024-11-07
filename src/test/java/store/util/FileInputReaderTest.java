package store.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("파일 입출력 테스트")
public class FileInputReaderTest {
    @ParameterizedTest(name = "{index} : {2}")
    @MethodSource("generateData")
    @DisplayName("파일 입출력을 통해 읽어 들인 상품 목록, 행사 목록이 파일 내용과 동일한지 랜덤으로 확인한다.")
    void containsContents(String path, String content, String message) {
        FileInputReader fileInputReader = new FileInputReader();
        assertThat(fileInputReader.readFile(path)).contains(content);
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("src/main/resources/products.md",
                        "비타민워터,1500,6,null", "products 랜덤1"),
                Arguments.of("src/main/resources/products.md",
                        "오렌지주스,1800,9,MD추천상품", "products 랜덤2"),
                Arguments.of("src/main/resources/promotions.md",
                        "탄산2+1,2,1,2024-01-01,2024-12-31", "promotions 랜덤1"),
                Arguments.of("src/main/resources/promotions.md",
                        "반짝할인,1,1,2024-11-01,2024-11-30", "promotions 랜덤2")
        );
    }
}
