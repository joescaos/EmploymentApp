package com.example.employmentApp.repositories;

import com.example.employmentApp.model.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentsRepository extends JpaRepository<Employment, Integer> {

}
