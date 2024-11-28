package store.domain.promotion;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;

public class Promotion {
    private final String name;
    private final int buy;
    private final int free;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private Promotion(String name, int buy, int free, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.free = free;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Promotion of(String name, int buy, int free, LocalDate startDate, LocalDate endDate) {
        return new Promotion(name, buy, free, startDate, endDate);
    }

    public boolean inPromotionPeriod() {
        return DateTimes.now().toLocalDate().isBefore(endDate) && DateTimes.now().toLocalDate().isAfter(startDate);
    }

    public int getFreeMore(int quantity) {
        int remaining = quantity % (buy + free);
        int freeMore = 0;
        if (remaining >= buy) {
            freeMore = free;
        }
        return freeMore;
    }

    public int getNotApplicable(int quantity) {
        return quantity % (buy + free);
    }

    public int getFree(int quantity) {
        return quantity / (buy + free);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "name='" + name + '\'' +
                ", buy=" + buy +
                ", free=" + free +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
