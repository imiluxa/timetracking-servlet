package ua.imiluxa.trainingproject.dto;

import ua.imiluxa.trainingproject.model.entity.Request;
import ua.imiluxa.trainingproject.model.entity.StatusActivity;
import ua.imiluxa.trainingproject.model.entity.User;

public class ActivityDTO {
    private long duration;
    private String goal;
    private String name;
    private StatusActivity statusActivity;
    private User user;
    private Request request;

    public ActivityDTO() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StatusActivity getStatusActivity() {
        return statusActivity;
    }

    public void setStatusActivity(StatusActivity statusActivity) {
        this.statusActivity = statusActivity;
    }

    public static class Builder {
        private long duration;
        private String goal;
        private String name;
        private StatusActivity statusActivity;
        private User user;
        private Request request;

        public static Builder builder() {
            return new Builder();
        }

        public Builder duration(long duration) {
            this.duration = duration;
            return this;
        }

        public Builder goal(String goal) {
            this.goal = goal;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder setStatusActivity(StatusActivity statusActivity) {
            this.statusActivity = statusActivity;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder request(Request request) {
            this.request = request;
            return this;
        }

        public ActivityDTO build() {

            ActivityDTO activityDTO = new ActivityDTO();

            activityDTO.setDuration(duration);
            activityDTO.setGoal(goal);
            activityDTO.setName(name);
            activityDTO.setStatusActivity(statusActivity);
            activityDTO.setRequest(request);
            activityDTO.setUser(user);

            return activityDTO;

        }
    }

    public static Builder builder() {
        return new Builder();
    }


}
