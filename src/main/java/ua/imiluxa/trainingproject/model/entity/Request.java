package ua.imiluxa.trainingproject.model.entity;

public class Request {
    private long id;
    private String action;
    private String status;
    private User user;
    private Activity activity;

    public Request(long id, String action, String status, User user, Activity activity) {
        this.id = id;
        this.action = action;
        this.status = status;
        this.user = user;
        this.activity = activity;
    }

    public Request() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        private long id;
        private String action;
        private String status;
        private User user;
        private Activity activity;

        public static Builder requestBuilder() {
            return new Builder();
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder status(String status) {
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

        public Request build() {
            return new Request(this);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    public Request(Builder builder) {
        this.id = builder.id;
        this.action = builder.action;
        this.status = builder.status;
        this.user = builder.user;
        this.activity = builder.activity;
    }
}
