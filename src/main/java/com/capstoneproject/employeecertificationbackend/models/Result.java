package com.capstoneproject.employeecertificationbackend.models;


import javax.persistence.*;


@Entity
public class Result {

    @Id
    @SequenceGenerator(
            name="result_sequence",
            sequenceName = "result_sequence"
    )
    @GeneratedValue(
            generator ="result_sequence",
            strategy =GenerationType.SEQUENCE
    )
    private Long id;


    @OneToOne
    private Employee employee_id;

    private int totalScore;
}
