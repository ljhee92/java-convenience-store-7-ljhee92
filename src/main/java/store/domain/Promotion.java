package store.domain;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;

public class Promotion implements DiscountStrategy {
    private final String name;
    private final int buy;
    private final int free;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, int buy, int free, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.buy = buy;
        this.free = free;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean exist(String name) {
        return name.equals(this.name);
    }

    boolean inPromotionPeriod() {
        return !this.startDate.isAfter(DateTimes.now().toLocalDate())
                && !this.endDate.isBefore(DateTimes.now().toLocalDate());
    }

    public int getBuy() {
        return buy;
    }

    public int getFree() {
        return free;
    }

    @Override
    public double getDiscountPrice(double price) {
        return 0;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "name='" + name + '\'' +
                ", buy=" + buy +
                ", get=" + free +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
