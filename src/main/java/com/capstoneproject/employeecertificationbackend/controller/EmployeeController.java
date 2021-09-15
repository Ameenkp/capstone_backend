package com.capstoneproject.employeecertificationbackend.controller;

import com.capstoneproject.employeecertificationbackend.exception.UserNotFoundException;
import com.capstoneproject.employeecertificationbackend.models.Employee;
import com.capstoneproject.employeecertificationbackend.models.Manager;
import com.capstoneproject.employeecertificationbackend.service.EmployeeService;
import com.capstoneproject.employeecertificationbackend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/empManagement")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ManagerService managerService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ManagerService managerService) {
        this.employeeService = employeeService;
        this.managerService = managerService;
    }


    @RequestMapping(
            value = "users/addUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody Employee employee) throws UserNotFoundException {

        if (employee.getEmpType().equals("manager")) {
            managerService.createNewManagerFromEmployee(employee);
        } else {
            employeeService.createNewUser(employee);
        }
    }
}
