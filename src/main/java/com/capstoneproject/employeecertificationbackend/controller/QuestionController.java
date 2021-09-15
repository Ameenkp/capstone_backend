package com.capstoneproject.employeecertificationbackend.controller;


import com.capstoneproject.employeecertificationbackend.models.QuestionForm;
import com.capstoneproject.employeecertificationbackend.models.Result;
import com.capstoneproject.employeecertificationbackend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RestController
@RequestMapping("api/questions")
public class QuestionController {

    @Autowired
    Result result;
    @Autowired
    QuizService qService;

    Boolean submitted = false;

    @GetMapping("getAll")
    public QuestionForm showQuestions(){

        return qService.getQuestions();
    }


//    @GetMapping("getAll")
//    public Result getResult() {
//        return result;
//    }


    @PostMapping("quiz")
    public QuestionForm quiz() {
        return qService.getQuestions();

    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm qForm, Model m) {
        if(!submitted) {
            result.setTotalCorrect(qService.getResult(qForm));
            qService.saveScore(result);
            submitted = true;
        }

        return "result.html";
    }

    @GetMapping("/score")
    public String score(Model m) {
        List<Result> sList = qService.getTopScore();
        m.addAttribute("sList", sList);

        return "scoreboard.html";
    }

}