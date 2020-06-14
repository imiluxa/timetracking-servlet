package ua.imiluxa.trainingproject.model.entity;

import java.util.stream.Stream;

public enum RequestActions {
    ADD("ADD"),
    END("END"),
    NONE("");

    private final String actionName;

    RequestActions(String actionName) {
        this.actionName = actionName;
    }

    @Override
    public String toString() {
        return actionName;
    }

    public static Stream<RequestActions> stream() {
        return Stream.of(RequestActions.values());
    }

    public static RequestActions getValue(String value) {
        if (value==null) return RequestActions.NONE;
        return RequestActions.stream()
                .filter(d -> d.actionName.equals(value))
                .findFirst()
                .get();
    }
}
