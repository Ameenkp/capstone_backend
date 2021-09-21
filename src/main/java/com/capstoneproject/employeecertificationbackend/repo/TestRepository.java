package com.capstoneproject.employeecertificationbackend.repo;

import com.capstoneproject.employeecertificationbackend.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test , Long> {

    public Test findTestByTitle(String title);
}
