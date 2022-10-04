package com.jianen.model;

public class mhl {
    private  String id;
    private  String username;
    private  String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public mhl() {
    }

    public mhl( String username, String password) {

        this.username = username;
        this.password = password;
    }
}
