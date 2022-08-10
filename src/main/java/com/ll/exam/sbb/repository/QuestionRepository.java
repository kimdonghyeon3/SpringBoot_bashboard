package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    public Question findById(int id);

    Question findBySubject(String s);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
}
