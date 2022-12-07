package com.example.employmentApp.repositories;

import com.example.employmentApp.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestsRepository extends JpaRepository<Request, Integer> {

}
