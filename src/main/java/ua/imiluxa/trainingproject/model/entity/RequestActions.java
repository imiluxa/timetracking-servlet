package ua.imiluxa.trainingproject.model.entity;

public enum RequestActions {
    ADD("Add"),
    END("End");

    private final String actionName;

    RequestActions(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String toString() {
        return actionName;
    }
}
