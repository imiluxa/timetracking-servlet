package ua.imiluxa.trainingproject.model.entity;

public enum RequestStatus {
    CONFIRMED("Confirmed"),
    DECLINED("Declined"),
    WAITING("Waiting");

    private final String statusName;

    RequestStatus(String statusName) { this.statusName = statusName; }

    @Override
    public String toString() { return statusName; }
}
