package edu.westga.cs3211.pirateinventory.model;
/**
 * Base class for all users of the system.
 * (CrewMate, Quartermaster, Cook, etc.)
 */
public abstract class User {

    private String username;
    private String password;
    private Role role;


    public User(String username, String password, Role role) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username is required");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password is required");
        }
        if (role == null) {
            throw new IllegalArgumentException("role is required");
        }

        this.username = username;
        this.password = password;
        this.role = role;
    }


    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }
}