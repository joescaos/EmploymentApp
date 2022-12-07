package com.example.employmentApp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private String file;

    private String comments;

    @OneToOne
    @JoinColumn(name = "idEmployment")
    private Employment employment;

    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;
}
