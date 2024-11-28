package store.util;

public enum RequestStatus {
    YES("Y"),
    NO("N")
    ;

    private final String requestValue;

    RequestStatus(String requestValue) {
        this.requestValue = requestValue;
    }

    public String getRequestValue() {
        return requestValue;
    }
}
