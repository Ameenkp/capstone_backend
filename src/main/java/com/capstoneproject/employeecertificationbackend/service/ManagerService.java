package com.capstoneproject.employeecertificationbackend.service;


import com.capstoneproject.employeecertificationbackend.exception.UserNotFoundException;
import com.capstoneproject.employeecertificationbackend.models.Admin;
import com.capstoneproject.employeecertificationbackend.models.Employee;
import com.capstoneproject.employeecertificationbackend.models.Manager;
import com.capstoneproject.employeecertificationbackend.repo.AdminRepository;
import com.capstoneproject.employeecertificationbackend.repo.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final EmployeePasswordGeneratorService employeePasswordGeneratorService;
    private final AdminRepository adminRepository;

    @Autowired
    public ManagerService(ManagerRepository managerRepository, EmployeePasswordGeneratorService employeePasswordGeneratorService, AdminRepository adminRepository) {
        this.managerRepository = managerRepository;
        this.employeePasswordGeneratorService = employeePasswordGeneratorService;
        this.adminRepository = adminRepository;
    }


    public void createNewManager(Manager manager) {
        manager.setPassword(employeePasswordGeneratorService.generateCommonLangPassword());
        Manager save = managerRepository.save(manager);
    }

    @Transactional
    public void createNewManagerFromEmployee(Employee employee) throws UserNotFoundException {

        Admin admin = adminRepository.findById(5000L).orElseThrow(UserNotFoundException::new);

        String name = employee.getName();
        String email = employee.getEmail();
        String empType = employee.getEmpType();
        String phoneNumber = employee.getPhoneNumber();
        String password = employeePasswordGeneratorService.generateCommonLangPassword();
        Manager newManager = new Manager(name,email,password,phoneNumber,empType);
        newManager.setAdmin(admin);
        admin.getManagers().add(newManager);
        managerRepository.save(newManager);
    }

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }
}
