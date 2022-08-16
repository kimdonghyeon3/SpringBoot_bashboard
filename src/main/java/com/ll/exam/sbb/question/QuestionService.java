package com.ll.exam.sbb.question;

import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question findById(int id) {
        return questionRepository.findById(id).get();
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
