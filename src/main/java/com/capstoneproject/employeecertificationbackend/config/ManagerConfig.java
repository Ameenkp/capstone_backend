package com.capstoneproject.employeecertificationbackend.config;

import com.capstoneproject.employeecertificationbackend.models.Manager;
import com.capstoneproject.employeecertificationbackend.repo.ManagerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ManagerConfig {

    @Bean
    CommandLineRunner commandLineRunner(ManagerRepository managerRepository) {
        return args -> {
            Manager manager1 = new Manager(
                    "balaji",
                    "balaji@adp.com",
                    "pass123",
                    "1234566",
                    "manager");
            Manager manager2 = new Manager(
                    "ameen",
                    "ameen@adp.com",
                    "123456",
                    "98772432",
                    "manager");


            managerRepository.saveAll(List.of(manager1, manager2));
        };
    }
}

