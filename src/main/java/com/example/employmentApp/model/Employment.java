package com.example.employmentApp.model;

import java.util.Date;

public class Employment {

  private Integer id;

  private String name;

  private String description;

  private Date date;

  private double salary;

  private int highlighted;

  private String category;

  public String getCategory() {
    return category;
  }

  private String details;

  private String status;

  private String image = "no-image.png";

  public void setCategory(String category) {
    this.category = category;
  }

  public void setHighlighted(int highlighted) {
    this.highlighted = highlighted;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public Integer getHighlighted() {
    return highlighted;
  }

  public void setDestacado(Integer destacado) {
    this.highlighted = destacado;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "Vacante{" +
        "id=" + id +
        ", nombre='" + name + '\'' +
        ", descripcion='" + description + '\'' +
        ", fecha=" + date +
        ", salario=" + salary +
        ", destacado=" + highlighted +
        ", categoria='" + category + '\'' +
        ", detalles='" + details + '\'' +
        ", estatus='" + status + '\'' +
        '}';
  }
}
