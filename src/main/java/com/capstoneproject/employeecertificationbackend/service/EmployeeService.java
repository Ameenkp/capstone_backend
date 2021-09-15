package com.capstoneproject.employeecertificationbackend.service;


import com.capstoneproject.employeecertificationbackend.exception.UserNotFoundException;
import com.capstoneproject.employeecertificationbackend.models.Admin;
import com.capstoneproject.employeecertificationbackend.models.Employee;
import com.capstoneproject.employeecertificationbackend.models.Manager;
import com.capstoneproject.employeecertificationbackend.repo.AdminRepository;
import com.capstoneproject.employeecertificationbackend.repo.EmployeeRepository;
import com.capstoneproject.employeecertificationbackend.repo.ManagerRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMailSender employeeMailSender;
    private final EmployeePasswordGeneratorService employeePasswordGeneratorService;
    private final ManagerRepository managerRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMailSender employeeMailSender, EmployeePasswordGeneratorService employeePasswordGeneratorService, ManagerRepository managerRepository, AdminRepository adminRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMailSender = employeeMailSender;
        this.employeePasswordGeneratorService = employeePasswordGeneratorService;
        this.managerRepository = managerRepository;
        this.adminRepository = adminRepository;
    }

    @PostConstruct
    @Transactional
    public void initDB() throws UserNotFoundException {
        Faker faker = new Faker();


        Admin admin = new Admin("admin","admin123@adp.com",faker.internet().password());


        Manager manager1 = new Manager(
                "sudappi",
                "sudappi@adp.com",
                "pass123",
                "1234566",
                "manager");
        manager1.setAdmin(admin);
        admin.getManagers().add(manager1);

        //student creation using faker
        String name = faker.name().fullName();
        String email = String.format("%s@ADP.com", name);
        String password = faker.internet().password();
        String phoneNumber = faker.phoneNumber().phoneNumber();
        String empType = "employee";
        Employee employee1 = new Employee(name,
                email,
                password,
                phoneNumber,
                empType);

        manager1.addReportee(employee1);
        employee1.setManager(manager1);

        Employee employee2 = new Employee(
                "sugunan",
                "sugunan@adp.com",
                faker.internet().password(),
                faker.phoneNumber().phoneNumber(),
                "employee");
        manager1.addReportee(employee2);
        employee2.setManager(manager1);

//        managerRepository.save(manager1);
        adminRepository.save(admin);


    }


    @Transactional
    public int createNewUser(Employee employee) throws UserNotFoundException {

        Optional<Employee> employeeByEmail = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if (employeeByEmail.isEmpty()) {
            employee.setPassword(employeePasswordGeneratorService.generateCommonLangPassword());
            Manager byId = managerRepository.findById(2001L).orElseThrow(UserNotFoundException::new);

            byId.addReportee(employee);
            employee.setManager(byId);
            managerRepository.save(byId);

            try {
                employeeMailSender.sendEmail(employee);
            } catch (MailException mailException) {
                mailException.getMessage();
            }
            return 1;
        }
        return 0;

    }


    public List<Employee> retrieveAllUsers() {

        return employeeRepository.findAll();
    }

    public List<Employee> retrieveAllUsersUnderManager(Long id) {

        Optional<Manager> byId = managerRepository.findById(id);
        return employeeRepository.findAllByManager(byId);
    }
}
