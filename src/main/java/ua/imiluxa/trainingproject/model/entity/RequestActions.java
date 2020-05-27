package ua.imiluxa.trainingproject.model.entity;

public enum RequestActions {
    ADD("ADD"),
    END("END");

    private final String actionName;

    RequestActions(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String toString() {
        return actionName;
    }
}
