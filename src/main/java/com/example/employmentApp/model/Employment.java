package com.example.employmentApp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "employments")
public class Employment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String description;

  private Date date;

  private Double salary;

  private Integer highlighted;

  private String details;

  private String status;

  private String image = "no-image.png";

  @OneToOne
  @JoinColumn(name = "idCategory")
  private Category category;

  public void resetImage() {
    this.image=null;
  }

}
