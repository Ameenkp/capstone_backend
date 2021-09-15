package com.capstoneproject.employeecertificationbackend.repo;

import com.capstoneproject.employeecertificationbackend.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
