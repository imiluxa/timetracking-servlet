package ua.imiluxa.trainingproject.model.entity;

import java.util.ArrayList;
import java.util.List;

public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Role role;
    private List<Activity> activitiesList = new ArrayList<>();
    private List<Request> requestList = new ArrayList<>();

    public User(long id, String firstName, String lastName, String userName,
                String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public List<Activity> getActivitiesList() {
        return activitiesList;
    }

    public void setActivitiesList(List<Activity> activitiesList) {
        this.activitiesList = activitiesList;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return true;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static class Builder {

        private long id;
        private String firstName;
        private String lastName;
        private String userName;
        private String email;
        private String password;
        private Role role;
        private List<Activity> activitiesList = new ArrayList<>();
        private List<Request> requestList = new ArrayList<>();

        public static Builder userbuilder() {
            return new Builder();
        }

        public Builder id(long id) {
            this.id = id;
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

        public Builder activitiesList(List<Activity> activitiesList) {
            this.activitiesList = activitiesList;
            return this;
        }

        public Builder requestList(List<Request> requestList) {
            this.requestList = requestList;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public User (Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userName = builder.userName;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
        this.activitiesList = builder.activitiesList;
        this.requestList = builder.requestList;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", username=" + userName
                + ", email=" + email +  ", act=" + activitiesList +
                ", req=" + requestList +"]";
    }
}
