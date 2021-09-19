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


    public void sendEmail(String email, String password) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(email);
            msg.setSubject("Successfully Created User");
            msg.setText("You Can Login Here :"+"http://localhost:4200/login"+"\n"
            +"Password :"+password);
            javaMailSender.send(msg);
    }
}
