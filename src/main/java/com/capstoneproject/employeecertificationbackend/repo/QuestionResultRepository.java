package com.capstoneproject.employeecertificationbackend.repo;

import com.capstoneproject.employeecertificationbackend.models.QuestionResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionResultRepository extends JpaRepository<QuestionResult, Long> {
}
