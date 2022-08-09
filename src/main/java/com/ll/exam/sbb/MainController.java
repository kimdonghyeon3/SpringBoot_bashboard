package com.ll.exam.sbb;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    static int num_inc = 0;

    @RequestMapping("/sbb")
    @ResponseBody
    public String index(){
        return "hi";
    }

    @GetMapping("/page1")
    @ResponseBody
    public String showPage1() {
        return """
                <form method="POST" action="/page2">
                    <input type="number" name="age" placeholder="나이" />
                    <input type="submit" value="page2로 POST 방식으로 이동" />
                </form>
                """;
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/plus")
    @ResponseBody
    public String showPlus(int a, int b) {
        return """
                <h1>결과 : %d</h1>
                <h1>안녕하세요, GET 방식으로 오셨군요.</h1>
                """.formatted(a + b);
    }

    @GetMapping("/minus")
    @ResponseBody
    public String showMinus(int a, int b) {
        return """
                <h1>결과 : %d</h1>
                <h1>안녕하세요, GET 방식으로 오셨군요.</h1>
                """.formatted(a - b);
    }

    @GetMapping("/increase")
    @ResponseBody
    public String showIncrease() {
        return """
                <h1>숫자 : %d</h1>
                <h1>안녕하세요, GET 방식으로 오셨군요.</h1>
                """.formatted(num_inc++);
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String showGugudan(int dan, int limit) {

        String str = "";
        for(int i = 1 ; i < limit ; i++){
            str += "<div>" + dan + " * " + i + " = " + (i*4) +"</div>";
        }

        return str;
    }

    @GetMapping("/mbti")
    @ResponseBody
    public String showGugudan(String name) {
        String mbti = "INFP";

        if(name.equals("홍길동")){
            mbti = "INFP";
        }else{
            mbti = "ENFP";
        }
        return """
                <h1>숫자 : %s</h1>
                <h1>안녕하세요, GET 방식으로 오셨군요.</h1>
                """.formatted(mbti);
    }

    @GetMapping("/saveSessionAge/{age}")
    @ResponseBody
    public String saveSessionAge(HttpSession session, @PathVariable String age) {

        session.setAttribute("age", age);

        return """
                <h1>저장된 나이 : %s</h1>
                <h1>안녕하세요, GET 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/getSessionAge")
    @ResponseBody
    public String getSessionAge(HttpSession session) {

        String age = session.getAttribute("age");

        return """
                <h1>저장된 나이 : %s</h1>
                <h1>안녕하세요, GET 방식으로 오셨군요.</h1>
                """.formatted(age);
    }
}

//

//
