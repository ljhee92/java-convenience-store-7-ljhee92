package store.service;

import store.dao.PromotionDAO;
import store.domain.Promotions;

public class PromotionService {
    private final PromotionDAO promotionDAO;

    public PromotionService(PromotionDAO promotionDAO) {
        this.promotionDAO = promotionDAO;
    }

    public Promotions getAllPromotions() {
        return new Promotions(promotionDAO.selectAllPromotions());
    }
}
