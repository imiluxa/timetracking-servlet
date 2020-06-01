package ua.imiluxa.trainingproject.dto;

import ua.imiluxa.trainingproject.model.entity.*;

public class ObjectDTO {
    private long duration;
    private String goal;
    private String name;
    private StatusActivity statusActivity;
    private RequestActions action;
    private RequestStatus status;
    private Activity activity;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;
    private User user;
    private Request request;

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

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public static class Builder {
        private long duration;
        private String goal;
        private String name;
        private StatusActivity statusActivity;
        private RequestActions action;
        private RequestStatus status;
        private Activity activity;
        private String username;
        private String firstName;
        private String lastName;
        private String password;
        private Role role;
        private User user;
        private Request request;

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

        public Builder request(Request request) {
            this.request = request;
            return this;
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

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        /*public ObjectDTO build() {
            ObjectDTO objectDTO = new ObjectDTO();
            objectDTO.setActivity();
        }*/
    }
}
