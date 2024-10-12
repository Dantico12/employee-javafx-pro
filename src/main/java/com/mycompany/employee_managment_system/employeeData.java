/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.employee_managment_system;

import java.util.Date;

/**
 *
 * @author PC
 */
public class employeeData {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender; 
    private String phone;
    private String position;
    private String image;
    private Date date;
    private Double salary;
    public employeeData(Integer id,String firstName,String lastName,String gender,String phone,String position,String image,Date date){
    this.id=id;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.phone=phone;
    this.position=position;
    this.image=image;
    this.date=date;
    }
     public employeeData(Integer id,String firstName,String lastName,String position,Double salary){
    this.id=id;
    this.firstName=firstName;
    this.lastName=lastName;
    this.position=position;
    this.salary=salary;
         
     }
    public Integer getId() {
    return id;
}

public String getFirstName() {
    return firstName;
}

public String getLastName() {
    return lastName;
}

public String getGender() {
    return gender;
}

public String getPhone() {
    return phone;
}

public String getPosition() {
    return position;
}

public String getImage() {
    return image;
}

public Date getDate() {
    return date;
}

public Double getSalary() { // Change to getSalary
    return salary;
}

    String getfirstName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}