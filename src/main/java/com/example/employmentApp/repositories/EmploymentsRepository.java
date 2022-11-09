package com.example.employmentApp.repositories;

import com.example.employmentApp.model.Employment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmploymentsRepository extends JpaRepository<Employment, Integer> {

    List<Employment> findByStatus(String status);

    List<Employment> findByHighlightedAndStatusOrderByIdDesc(int highlighted, String status);

    List<Employment> findBySalaryBetween(double s1, double s2);

    List<Employment> findByStatusIn(List<String> statuses);

    List<Employment> findByHighlightedAndStatus(int highlighted, String status);

}
