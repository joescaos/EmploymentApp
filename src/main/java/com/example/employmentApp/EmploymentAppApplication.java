package com.example.employmentApp;

import com.example.employmentApp.model.Category;
import com.example.employmentApp.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
public class EmploymentAppApplication implements CommandLineRunner {

	@Autowired
	private CategoriesRepository categoriesRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmploymentAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*save();
		get(1);
		get(2);
		update(2);
		update(3); */
	}

	private void save() {
		Category category = new Category();
		category.setName("Finanzas");
		category.setDescription("Ofertas laborales relacionadas con finanzas y contabilidad");
		categoriesRepository.save(category);
	}

	private void get(Integer id) {
		Optional<Category> category = categoriesRepository.findById(id);
		if(category.isPresent())
			System.out.println(category.get());
		else
			System.out.println("categoria no encontrada");
	}

	private void update(Integer id) {
		Optional<Category> category2 = categoriesRepository.findById(id);
		if(category2.isPresent()) {
			Category categoryToUpdate = category2.get();
			categoryToUpdate.setName("Ingeniería de Software");
			categoryToUpdate.setDescription("Ofertas relacionadas con las TI");
			categoriesRepository.save(categoryToUpdate);
		} else {
			System.out.println("categoria no encontrada");
		}
	}
}
