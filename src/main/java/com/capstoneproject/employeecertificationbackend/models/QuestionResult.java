package com.capstoneproject.employeecertificationbackend.models;


    import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
    @Table(name = "results")
    public class QuestionResult {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private int totalCorrect = 0;

        public QuestionResult() {
        }

        public QuestionResult(Long id, String username, int totalCorrect) {
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
