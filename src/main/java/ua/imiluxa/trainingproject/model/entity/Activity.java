package ua.imiluxa.trainingproject.model.entity;

import java.util.List;
import java.util.Map;

public class Activity {

    private long idactivity;
    private long duration;
    private String goal;
    private String name;
    private StatusActivity statusActivity;
    private User user;
    private List<Request> request;
    private Map<Long, Request> requestMap;

    public Map<Long, Request> getRequestMap() {
        return requestMap;
    }

    public void setRequestMap(Map<Long, Request> requestMap) {
        this.requestMap = requestMap;
    }

    public Activity(long idactivity, long duration, String goal,
                    String name, StatusActivity statusActivity,
                    User user, List<Request> request, Map<Long, Request> requestMap) {
        this.idactivity = idactivity;
        this.duration = duration;
        this.goal = goal;
        this.name = name;
        this.statusActivity = statusActivity;
        this.user = user;
        this.request = request;
        this.requestMap = requestMap;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Request> getRequest() {
        return request;
    }

    public void setRequest(List<Request> request) {
        this.request = request;
    }

    public long getIdactivity() {
        return idactivity;
    }

    public void setIdactivity(long idactivity) {
        this.idactivity = idactivity;
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
        private long idactivity;
        private long duration;
        private String goal;
        private String name;
        private StatusActivity statusActivity;
        private User user;
        private List<Request> request;
        private Map<Long, Request> requestMap;

        public static Builder activityBuilder() {
            return new Builder();
        }

        public Builder idactivity(long idactivity) {
            this.idactivity = idactivity;
            return this;
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

        public Builder statusActivity(StatusActivity statusStrActivity) {
            this.statusActivity = statusStrActivity;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder request(List<Request> request) {
            this.request = request;
            return this;
        }

        public Builder requestMap(Map<Long, Request> requestMap) {
            this.requestMap = requestMap;
            return this;
        }

        public Activity build() {
            return new Activity(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Activity(Builder builder) {
        this.idactivity = builder.idactivity;
        this.duration = builder.duration;
        this.goal = builder.goal;
        this.name = builder.name;
        this.statusActivity = builder.statusActivity;
        this.user = builder.user;
        this.request = builder.request;
        this.requestMap = builder.requestMap;
    }
}
