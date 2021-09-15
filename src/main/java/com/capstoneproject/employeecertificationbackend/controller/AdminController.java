package com.capstoneproject.employeecertificationbackend.controller;


import com.capstoneproject.employeecertificationbackend.exception.UserNotFoundException;
import com.capstoneproject.employeecertificationbackend.models.Manager;
import com.capstoneproject.employeecertificationbackend.service.AdminService;
import com.capstoneproject.employeecertificationbackend.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final ManagerService managerService;
    private final AdminService adminService;

    @Autowired
    public AdminController(ManagerService managerService, AdminService adminService) {
        this.managerService = managerService;
        this.adminService = adminService;
    }


    @GetMapping("{id}")
    public List<Manager> getAllManagers(@PathVariable Long id) throws UserNotFoundException {

        return adminService.retrieveAllManagers(id);
    }
}
