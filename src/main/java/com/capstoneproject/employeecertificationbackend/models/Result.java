package com.capstoneproject.employeecertificationbackend.models;


import org.springframework.stereotype.Component;

import javax.persistence.*;


@Component()
@Table(name = "result")
@Entity(name = "result")
public class Result {

    @Id
    @SequenceGenerator(
            name = "result_sequence",
            sequenceName = "result_sequence"
    )
    @GeneratedValue(
            generator = "result_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String username;
    private int totalCorrect = 0;

    public Result() {
    }

    public Result(Long id, String username, int totalCorrect) {
        this.id = id;
        this.username = username;
        this.totalCorrect = totalCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
    }
}
