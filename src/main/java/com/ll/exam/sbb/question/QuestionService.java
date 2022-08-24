package com.ll.exam.sbb.question;

import com.ll.exam.sbb.DataNotFoundException;
import com.ll.exam.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question getQuestion(long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("no %d question not found,".formatted(id)));
    }

    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        if(kw == null){
            return questionRepository.findAll(pageable);
        }

        //return this.questionRepository.findBySubjectContains(kw, pageable);
        //return questionRepository.findBySubjectContainsOrContentContainsOrAuthor_usernameContains(kw, kw, kw, pageable);

        //return questionRepository.findDistinctBySubjectContainsOrContentContainsOrAuthor_usernameContainsOrAnswerList_contentContains(kw, kw, kw, kw, pageable);

        return questionRepository.findDistinctBySubjectContainsOrContentContainsOrAuthor_usernameContainsOrAnswerList_contentContainsOrAnswerList_author_usernameContains(kw, kw, kw, kw, kw, pageable);
    }

    public void create(String subject, String content, SiteUser user) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(user);
        this.questionRepository.save(q);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void delete(Question question){
        questionRepository.delete(question);
    }

    public void vote(Question question, SiteUser siteUser){
        question.getVoter().add(siteUser);
        questionRepository.save(question);
    }
}
