package ua.imiluxa.trainingproject.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Activity {

    private long idactivity;
    private long duration;
    private String goal;
    private String name;
    private StatusActivity statusActivity;
    private List<User> userList = new ArrayList<>();
    private List<Request> requestList = new ArrayList<>();

    public Activity(long idactivity, long duration, String goal,
                    String name, StatusActivity statusActivity,
                    List<User> userList, List<Request> requestList) {
        this.idactivity = idactivity;
        this.duration = duration;
        this.goal = goal;
        this.name = name;
        this.statusActivity = statusActivity;
        this.userList = userList;
        this.requestList = requestList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
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
        private List<User> userList = new ArrayList<>();
        private List<Request> requestList = new ArrayList<>();

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

        public Builder statusActivity(StatusActivity statusActivity) {
            this.statusActivity = statusActivity;
            return this;
        }

        public Builder userList(List<User> userList) {
            this.userList = userList;
            return this;
        }

        public Builder requestList(List<Request> requestList) {
            this.requestList = requestList;
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
        this.userList = builder.userList;
        this.requestList = builder.requestList;
    }
}
