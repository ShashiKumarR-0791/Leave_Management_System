package model;

import java.time.LocalDateTime;

public class User {
    private String username;
    private String password;
    private String role;
    private int failedAttempts = 0;
    private LocalDateTime lockoutTime = null;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public int getFailedAttempts() { return failedAttempts; }
    public void setFailedAttempts(int failedAttempts) { this.failedAttempts = failedAttempts; }

    public LocalDateTime getLockoutTime() { return lockoutTime; }
    public void setLockoutTime(LocalDateTime lockoutTime) { this.lockoutTime = lockoutTime; }

    @Override
    public String toString() {
        return username + "," + password + "," + role + "," + failedAttempts + "," +
                (lockoutTime != null ? lockoutTime.toString() : "");
    }
}