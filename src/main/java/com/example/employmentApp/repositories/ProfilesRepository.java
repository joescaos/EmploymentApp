package com.example.employmentApp.repositories;

import com.example.employmentApp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<Profile, Integer> {
}
