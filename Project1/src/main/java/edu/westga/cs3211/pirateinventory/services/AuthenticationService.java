package edu.westga.cs3211.pirateinventory.services;

import java.util.HashMap;
import java.util.Map;

import edu.westga.cs3211.pirateinventory.model.CrewMate;
import edu.westga.cs3211.pirateinventory.model.Quartermaster;
import edu.westga.cs3211.pirateinventory.model.User;

/**
 * Simple in-memory authentication service.
 */
public class AuthenticationService {

    private final Map<String, User> users;

    public AuthenticationService() {
        this.users = new HashMap<>();

        // Hard-coded users for the project.
        // Make sure these match your README credentials.
        this.users.put("crew1", new CrewMate("crew1", "password"));
        this.users.put("qm1", new Quartermaster("qm1", "password"));
    }

    /**
     * Tries to authenticate the user.
     *
     * @param username the username
     * @param password the password
     * @return the matching User if credentials are correct, otherwise null
     */
    public User authenticate(String username, String password) {
        User user = this.users.get(username);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }
}