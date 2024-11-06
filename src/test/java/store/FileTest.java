package store;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("파일 입력 테스트")
public class FileTest {
    @ParameterizedTest(name = "{index} : {2}")
    @CsvSource(value = {"src/main/resources/products.md : 비타민워터,1500,6,null : products-1",
            "src/main/resources/products.md : 오렌지주스,1800,9,MD추천상품 : products-2",
            "src/main/resources/promotions.md : 탄산2+1,2,1,2024-01-01,2024-12-31 : promotions-1",
            "src/main/resources/promotions.md : 반짝할인,1,1,2024-11-01,2024-11-30 : promotions-2"}
            , delimiter = ':')
    @DisplayName("파일 입력을 통해 읽어 들인 상품 목록, 행사 목록이 파일 내용과 동일한지 랜덤으로 확인한다.")
    void containsContents(String path, String content, String message) {
        FileInputReader fileInputReader = new FileInputReader();

        assertThat(fileInputReader.openFile(path))
                .contains(content);
    }
}
