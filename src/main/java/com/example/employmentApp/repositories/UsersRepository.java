package com.example.employmentApp.repositories;

import com.example.employmentApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
}
