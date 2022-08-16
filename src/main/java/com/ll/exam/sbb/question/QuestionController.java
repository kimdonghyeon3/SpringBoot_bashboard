package com.ll.exam.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor // 생성자 주입
public class QuestionController
{

    private final QuestionService questionService;

    @RequestMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = questionService.findAll();

        model.addAttribute("questionList", questionList);

        return "question_list";
    }

    @RequestMapping("list")
    @ResponseBody
    public String showList() {
        questionService.findById(1);

        return "HI";
    }
}
