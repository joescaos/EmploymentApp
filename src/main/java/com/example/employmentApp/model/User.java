package com.example.employmentApp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String name;

    private String email;

    private String password;

    private Integer status;

    private LocalDate dateCreated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "userProfile",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idProfile"))
    private List<Profile> profiles;

    public void addProfiles(Profile profile) {
        if(Objects.isNull(profiles)) {
            profiles = new LinkedList<Profile>();
        }
        profiles.add(profile);
    }
}
