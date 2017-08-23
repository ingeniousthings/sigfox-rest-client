package ingeniousthings.sigfox.model;

import java.util.List;

public class User {

    public final String firstName;
    public final String lastName;
    public final String email;
    public final String timezone;
    public final String creationTime;
    public final String creationDate;
    public final long lastLogin;
    public final String lastLoginDate;
    public final List<UserRole> userRoles;

    public User(String firstName, String lastName, String email, String timezone, String creationTime, String creationDate, long lastLogin, String lastLoginDate, List<UserRole> userRoles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.timezone = timezone;
        this.creationTime = creationTime;
        this.creationDate = creationDate;
        this.lastLogin = lastLogin;
        this.lastLoginDate = lastLoginDate;
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "User{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", timezone='" + timezone + '\'' +
            ", creationTime='" + creationTime + '\'' +
            ", creationDate='" + creationDate + '\'' +
            ", lastLogin=" + lastLogin +
            ", lastLoginDate='" + lastLoginDate + '\'' +
            ", userRoles=" + userRoles +
            '}';
    }
}
