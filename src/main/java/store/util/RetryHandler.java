package store.util;

import java.util.function.Supplier;

public class RetryHandler {
    public <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return repeat(supplier);
        }
    }

    public void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            repeat(runnable);
        }
    }
}
