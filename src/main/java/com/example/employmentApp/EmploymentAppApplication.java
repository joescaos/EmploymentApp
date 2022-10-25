package com.example.employmentApp;

import com.example.employmentApp.model.Category;
import com.example.employmentApp.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class EmploymentAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmploymentAppApplication.class, args);
	}
}
