package com.example.employmentApp.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    private String file;

    private String comments;

    @OneToOne
    @JoinColumn(name = "idEmployment")
    private Employment employment;

    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;
}
