package com.capstoneproject.employeecertificationbackend.service;

import com.capstoneproject.employeecertificationbackend.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//Service to send mail when an user profile is created
@Service
public class EmployeeMailSender {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmployeeMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(Employee employee) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(employee.getEmail());
            msg.setSubject("Successfully Created User");
            msg.setText("Change Password With this URL :"+"http://localhost:8080/api/empManagement/users/addUser"+"\n"
            +"Password :"+employee.getPassword());
            javaMailSender.send(msg);
    }
}
