package ua.imiluxa.trainingproject.model.entity;

import java.util.stream.Stream;

public enum RequestStatus {
    CONFIRMED("CONFIRMED"),
    DECLINED("DECLINED"),
    WAITING("WAITING"),
    NONE("");

    private final String statusName;

    RequestStatus(String statusName) { this.statusName = statusName; }

    @Override
    public String toString() { return statusName; }

    public static Stream<RequestStatus> stream() {
        return Stream.of(RequestStatus.values());
    }

    public static RequestStatus getValue(String value) {
        if (value==null) return RequestStatus.NONE;
        return RequestStatus.stream()
                .filter(d -> d.statusName.equals(value))
                .findFirst()
                .get();
    }
}
