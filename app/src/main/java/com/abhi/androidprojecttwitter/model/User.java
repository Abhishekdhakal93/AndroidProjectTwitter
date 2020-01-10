package com.abhi.twitter.model;

public class User {
    String email;
    String password;
    String username;
    String image;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String username, String image) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.image = image;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getImageName() {
        return image;
    }
}
