package com.example.employmentApp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String profile;

}
