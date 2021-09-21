package com.capstoneproject.employeecertificationbackend.controller;


import com.capstoneproject.employeecertificationbackend.dto.TestDto;
import com.capstoneproject.employeecertificationbackend.models.Test;
import com.capstoneproject.employeecertificationbackend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("test/getAll")
    public List<Test> getAllTest(){
        return testService.getAllTest();
    }

    @GetMapping("test/{getbyid}")
    public ResponseEntity<Optional<Test>> getById(@PathVariable("getbyid") Long id){
        Optional<Test> testById = testService.getTestById(id);
        return testById.isPresent() ?
                new ResponseEntity<>(testById, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("test/addTest")
    public void createNewTest(@RequestBody TestDto test){

       testService.createNewTest(test);
    }
}
