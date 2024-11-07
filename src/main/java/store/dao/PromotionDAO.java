package store.dao;

import store.domain.Promotion;
import store.mapper.PromotionMapper;
import store.util.FileInputReader;

import java.util.ArrayList;
import java.util.List;

public class PromotionDAO {
    private static final String PROMOTION_FILE_PATH = "src/main/resources/promotions.md";

    private final PromotionMapper promotionMapper;

    public PromotionDAO(PromotionMapper promotionMapper) {
        this.promotionMapper = promotionMapper;
    }

    public List<Promotion> selectAllPromotions() {
        List<String> promotionsForFileByLine  = load();
        List<Promotion> promotions = new ArrayList<>();
        for (String line : promotionsForFileByLine) {
            Promotion promotion = promotionMapper.mapToPromotion(line);
            promotions.add(promotion);
        }
        return promotions;
    }

    private List<String> load() {
        FileInputReader fileInputReader = new FileInputReader();
        List<String> contentsByLine = fileInputReader.readFile(PROMOTION_FILE_PATH);
        contentsByLine.removeFirst();
        return contentsByLine;
    }
}
