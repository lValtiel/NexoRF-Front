package com.devemersonc.model;

public class CreateUser {
    private String username;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private String rut;

    public CreateUser(String username, String name, String lastName, String password, String email, String rut) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.rut = rut;
    }

    public CreateUser(String username, String name, String lastName, String email, String rut) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.rut = rut;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
