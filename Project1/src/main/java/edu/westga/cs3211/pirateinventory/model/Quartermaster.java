package edu.westga.cs3211.pirateinventory.model;

public class Quartermaster extends User {

    public Quartermaster(String username, String password) {
        super(username, password, Role.QUARTERMASTER);
    }
}