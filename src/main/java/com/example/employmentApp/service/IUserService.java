package com.example.employmentApp.service;

import com.example.employmentApp.model.User;

import java.util.List;

public interface IUserService {

    void saveUser(User user);

    void deleteUser(Integer userId);

    List<User> getAllUsers();

    User findUserByUsername(String username);
}
