package com.example.employmentApp.service.impl;

import com.example.employmentApp.model.Employment;
import com.example.employmentApp.service.IEmploymentService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmploymentServiceImpl implements IEmploymentService {

    private List<Employment> employmentList;

    public EmploymentServiceImpl() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        employmentList = new LinkedList<Employment>();
        try {
            Employment v1 = new Employment();
            v1.setId(1);
            v1.setCategory("IT");
            v1.setName("Ingeniero de Software");
            v1.setDescription("Encargado de desarrollar software seguro y escalable");
            v1.setDate(sdf.parse("08-02-2019"));
            v1.setSalary(9700);
            v1.setHighlighted(1);
            v1.setImage("logo1.png");
            v1.setStatus("Aprobada");

            Employment v2 = new Employment();
            v2.setId(2);
            v2.setCategory("IT");
            v2.setName("Ingeniero de Sistemas");
            v2.setDescription("Encargado de verificar los sistemas y su funcionamiento");
            v2.setDate(sdf.parse("10-09-2019"));
            v2.setSalary(9700);
            v2.setHighlighted(0);
            v2.setImage("logo2.png");
            v2.setStatus("Eliminada");

            Employment v3 = new Employment();
            v3.setId(3);
            v3.setCategory("IT");
            v3.setName("Ingeniero Devops");
            v3.setDescription("Encargado de la cultura devops en la organizacion");
            v3.setDate(sdf.parse("11-05-2019"));
            v3.setSalary(9900);
            v3.setHighlighted(0);
            v3.setImage("logo3.png");
            v3.setStatus("Aprobada");

            Employment v4 = new Employment();
            v4.setId(4);
            v4.setCategory("IT");
            v4.setName("Ingeniero de Soporte");
            v4.setDescription("Encargado de dar soporte a nuestros clientes");
            v4.setDate(sdf.parse("11-05-2019"));
            v4.setSalary(8800);
            v4.setHighlighted(0);
            v4.setImage("logo4.png");
            v4.setStatus("Creada");

            employmentList.add(v1);
            employmentList.add(v2);
            employmentList.add(v3);
            employmentList.add(v4);

        } catch (ParseException e) {
            System.out.printf("Error: %s \n", e.getMessage());
        }
    }

    @Override
    public List<Employment> findAll() {
        return this.employmentList;
    }

    @Override
    public Employment getById(int id) {
        for (Employment employment : employmentList) {
            if(employment.getId() == id){
                return employment;
            }
        }
        return null;
    }

    @Override
    public void save(Employment employment) {
        employmentList.add(employment);
    }
}
