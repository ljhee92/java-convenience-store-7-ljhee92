package store.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public String getName() {
        return name;
    }

    public int getBuy() {
        return buy;
    }

    public int getFree() {
        return free;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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
