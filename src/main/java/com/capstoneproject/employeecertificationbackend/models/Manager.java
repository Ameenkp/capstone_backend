package com.capstoneproject.employeecertificationbackend.models;

import javax.persistence.*;

@Entity
public class Manager {

    @Id
    @SequenceGenerator(
            name="employee_sequence",
            sequenceName = "employee_sequence"
    )
    @GeneratedValue(
            generator ="employee_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "manager_id",
            nullable = false,
            updatable = false
    )
    private long manager_id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name="password",
            nullable = false
    )
    private String password;


    @Column(
            name = "emp_type",
            nullable = false
    )
    private String empType;

    public Manager() {
    }

    public Manager(long manager_id, String name, String email, String password, String empType) {
        this.manager_id = manager_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.empType = empType;
    }

    public long getManager_id() {
        return manager_id;
    }

    public void setManager_id(long manager_id) {
        this.manager_id = manager_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "manager_id=" + manager_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", empType='" + empType + '\'' +
                '}';
    }
}
