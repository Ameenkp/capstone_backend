package com.capstoneproject.employeecertificationbackend.service;


import com.capstoneproject.employeecertificationbackend.models.Question;
import com.capstoneproject.employeecertificationbackend.models.QuestionBank;
import com.capstoneproject.employeecertificationbackend.repo.QuestionRepository;
import com.capstoneproject.employeecertificationbackend.repo.QuestionResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {


    private final QuestionRepository questionRepository;
    private final QuestionResultRepository questionResultRepository;
    private final QuestionBank questionBank;

    @Autowired
    public QuizService(QuestionRepository questionRepository, QuestionResultRepository questionResultRepository, QuestionBank questionBank) {
        this.questionRepository = questionRepository;
        this.questionResultRepository = questionResultRepository;
        this.questionBank = questionBank;
    }

    public QuestionBank getQuestions() {
        List<Question> allQues = questionRepository.findAll();
        List<Question> qList = new ArrayList<>();

        Random random = new Random();

        for(int i=0; i<15; i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }

        questionBank.setQuestions(qList);

        return questionBank;
    }

    public int getResult(QuestionBank qBank) {
        int correct = 0;

        for(Question q: qBank.getQuestions())
            if(q.getAns() == q.getChosen())
                correct++;

        return correct;
    }

//    public void saveScore(Result result) {
//        Result saveResult = new Result();
//        saveResult.setUsername(result.getUsername());
//        saveResult.setTotalCorrect(result.getTotalCorrect());
//        rRepo.save(saveResult);
//    }
//
//    public List<Result> getTopScore() {
//        List<Result> sList = rRepo.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
//
//        return sList;
//    }
}
