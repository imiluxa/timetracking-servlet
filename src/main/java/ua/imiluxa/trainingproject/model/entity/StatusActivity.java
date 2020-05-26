package ua.imiluxa.trainingproject.model.entity;

public enum StatusActivity {
    WAITING("Waiting"),
    INPROGRESS("Inprogress"),
    COMPLETED("Completed");

    private final String statusName;

    StatusActivity(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return statusName;
    }
}
