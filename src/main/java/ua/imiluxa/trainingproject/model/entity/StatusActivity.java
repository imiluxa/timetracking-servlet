package ua.imiluxa.trainingproject.model.entity;

public enum StatusActivity {
    WAITING("WAITING"),
    INPROGRESS("INPROGRESS"),
    COMPLETED("COMPLETED");

    private final String statusName;

    StatusActivity(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }
}
