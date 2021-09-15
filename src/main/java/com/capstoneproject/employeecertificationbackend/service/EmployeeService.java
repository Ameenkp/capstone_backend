package com.capstoneproject.employeecertificationbackend.service;


import com.capstoneproject.employeecertificationbackend.models.Employee;
import com.capstoneproject.employeecertificationbackend.repo.EmployeeRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMailSender employeeMailSender;
    private final EmployeePasswordGeneratorService employeePasswordGeneratorService;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMailSender employeeMailSender, EmployeePasswordGeneratorService employeePasswordGeneratorService) {
        this.employeeRepository = employeeRepository;
        this.employeeMailSender = employeeMailSender;
        this.employeePasswordGeneratorService = employeePasswordGeneratorService;
    }

    @PostConstruct
    public void initDB(){
        Faker faker = new Faker();

        //Manager Creation Using Faker


        for (int i = 0; i < 5; i++) {
            //student creation using faker
            String name = faker.name().fullName();
            String email = String.format("%s@ADP.com", name);
            String password = faker.internet().password();
            String phoneNumber = faker.phoneNumber().phoneNumber();
            String empType = "employee";
            Employee employee = new Employee(name, email, password, phoneNumber, empType);
            employeeRepository.save(employee);
        }
    }


    public int createNewUser(Employee employee){

        Optional<Employee> employeeByEmail = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if(employeeByEmail.isEmpty()){
            employee.setPassword(employeePasswordGeneratorService.generateCommonLangPassword());
            employeeRepository.save(employee);
            try {
                employeeMailSender.sendEmail(employee);
            }
            catch (MailException mailException){
                mailException.getMessage();
            }
            return 1;
        }
        return 0;

    }


    public List<Employee> retrieveAllUsers() {

        return employeeRepository.findAll();
    }
}
