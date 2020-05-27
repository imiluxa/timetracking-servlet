package ua.imiluxa.trainingproject.dto;

import ua.imiluxa.trainingproject.model.entity.Role;
import ua.imiluxa.trainingproject.model.entity.StatusActivity;

public class ActivityDTO {

    private long idactivity;
    private long duration;
    private String goal;
    private String name;
    private StatusActivity statusActivity;
    private long user_id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Role role;
    private long request_id;
    private String action;
    private String status;

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

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public long getRequest_id() {
        return request_id;
    }

    public void setRequest_id(long request_id) {
        this.request_id = request_id;
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

    public static class Builder {
        private long idactivity;
        private long duration;
        private String goal;
        private String name;
        private StatusActivity statusActivity;
        private long user_id;
        private String firstName;
        private String lastName;
        private String userName;
        private String email;
        private String password;
        private Role role;
        private long request_id;
        private String action;
        private String status;

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

        public Builder user_id(long user_id) {
            this.user_id = user_id;
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

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder request_id(long request_id) {
            this.request_id = request_id;
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

        public ActivityDTO build() {
            return new ActivityDTO(this);
        }

    }

    ActivityDTO(Builder builder) {
        this.idactivity = builder.idactivity;
        this.duration = builder.duration;
        this.goal = builder.goal;
        this.name = builder.name;
        this.statusActivity = builder.statusActivity;
        this.user_id = builder.user_id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userName = builder.userName;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.request_id = builder.request_id;
        this.action = builder.action;
        this.status = builder.status;
    }
}
