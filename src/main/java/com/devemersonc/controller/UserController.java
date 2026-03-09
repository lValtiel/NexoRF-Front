package com.devemersonc.controller;

import com.devemersonc.model.CreateUser;
import com.devemersonc.model.RegisterUserRequest;
import com.devemersonc.model.UserResponseDTO;
import com.devemersonc.model.ValidationErrorUserDTO;
import com.devemersonc.service.UserService;

import java.util.List;
import java.util.Map;

public class UserController {
    private final UserService userService = new UserService();

    public Map<String, String> createUser(RegisterUserRequest registerUserRequest) throws Exception{
        return userService.createUser(registerUserRequest);
    }

    public List<UserResponseDTO> getUsers() throws Exception{
        return userService.getUsers();
    }

    public void updateUser(Long user_id, CreateUser userUpdateDTO) throws Exception{
        userService.updateUser(user_id, userUpdateDTO);
    }

    public void deleteUser(Long user_id) throws Exception{
        userService.deleteUser(user_id);
    }
}
