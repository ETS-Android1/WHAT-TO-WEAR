package com.tasdasdsaduiz.what_to_wear;

import java.io.Serializable;

public class UserAccount implements Serializable{

    private String username;
    private String email;
    private String password;

    public UserAccount(String user, String email, String pass){
        this.email = email;
        this.password = pass;
        this.username = user;
    }

    public UserAccount(String username, String password){
        this.email = null;
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
