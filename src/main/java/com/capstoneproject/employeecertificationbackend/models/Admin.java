package com.capstoneproject.employeecertificationbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "admin")
@Table(name = "admin")
public class Admin {

    @Id
    @SequenceGenerator(
            name="admin_sequence",
            sequenceName = "admin_sequence",
            initialValue = 5000,
            allocationSize = 10
    )
    @GeneratedValue(
            generator ="admin_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @Column(
            name = "admin_id",
            nullable = false,
            updatable = false
    )
    private Long admin_id;
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

    @JsonIgnore
    @Column(
            name="password",
            nullable = false
    )
    private String password;

    @OneToMany(mappedBy = "admin",
            cascade=CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Manager> managers = new ArrayList<>();


    public Admin() {
    }

    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
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


    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
