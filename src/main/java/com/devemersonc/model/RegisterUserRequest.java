package com.devemersonc.model;

public class RegisterUserRequest {
    private CreateUser user;
    private RoleRequest role;

    public RegisterUserRequest(CreateUser user, RoleRequest role) {
        this.user = user;
        this.role = role;
    }

    public CreateUser getUser() {
        return user;
    }

    public void setUser(CreateUser user) {
        this.user = user;
    }

    public RoleRequest getRole() {
        return role;
    }

    public void setRole(RoleRequest role) {
        this.role = role;
    }
}
