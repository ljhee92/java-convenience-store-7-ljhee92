package store.util;

public class RetryHandler {
    public void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            repeat(runnable);
        }
    }
}
