package ua.imiluxa.trainingproject.model.entity;

public enum RequestStatus {
    CONFIRMED("CONFIRMED"),
    DECLINED("DECLINED"),
    WAITING("WAITING");

    private final String statusName;

    RequestStatus(String statusName) { this.statusName = statusName; }

    @Override
    public String toString() { return statusName; }
}
