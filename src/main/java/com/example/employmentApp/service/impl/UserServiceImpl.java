package com.example.employmentApp.service.impl;

import com.example.employmentApp.model.User;
import com.example.employmentApp.repositories.UsersRepository;
import com.example.employmentApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void saveUser(User user) {
      usersRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User findUserByUsername(String username) {
        return usersRepository.findUserByUserName(username);
    }
}
