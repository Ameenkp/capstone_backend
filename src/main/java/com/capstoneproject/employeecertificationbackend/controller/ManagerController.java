package com.capstoneproject.employeecertificationbackend.controller;


import com.capstoneproject.employeecertificationbackend.models.Employee;
import com.capstoneproject.employeecertificationbackend.models.Manager;
import com.capstoneproject.employeecertificationbackend.service.EmployeeService;
import com.capstoneproject.employeecertificationbackend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/manager")
public class ManagerController {

    private final EmployeeService employeeService;
    private final ManagerService managerService;

    @Autowired
    public ManagerController(EmployeeService employeeService, ManagerService managerService) {
        this.employeeService = employeeService;
        this.managerService = managerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public List<Employee> getUsers(@PathVariable("id") Long id){

        return employeeService.retrieveAllUsersUnderManager(id);
    }

    @GetMapping("getmanagers")
    public List<Manager> getManagers(){
        return managerService.getAllManagers();
    }

}
