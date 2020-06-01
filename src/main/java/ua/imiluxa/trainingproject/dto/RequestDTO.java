package ua.imiluxa.trainingproject.dto;

import ua.imiluxa.trainingproject.model.entity.Activity;
import ua.imiluxa.trainingproject.model.entity.RequestActions;
import ua.imiluxa.trainingproject.model.entity.RequestStatus;
import ua.imiluxa.trainingproject.model.entity.User;

public class RequestDTO {
    private RequestActions action;
    private RequestStatus status;
    private User user;
    private Activity activity;

    public RequestActions getAction() {
        return action;
    }

    public void setAction(RequestActions action) {
        this.action = action;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public static class Builder {
        private RequestActions action;
        private RequestStatus status;
        private User user;
        private Activity activity;

        public static Builder requestBuilder() {
            return new Builder();
        }

        public Builder action(RequestActions action) {
            this.action = action;
            return this;
        }

        public Builder status(RequestStatus status) {
            this.status = status;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder activity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public RequestDTO build() {
            RequestDTO reqDTO = new RequestDTO();
            reqDTO.setAction(action);
            reqDTO.setStatus(status);
            reqDTO.setUser(user);
            reqDTO.setActivity(activity);

            return reqDTO;
        }

    }

    public static Builder builder() { return new Builder(); }
}
