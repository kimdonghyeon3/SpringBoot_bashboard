package com.ll.exam.sbb;

import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/saveSession/{name}/{value}")
    @ResponseBody
    public String saveSession(@PathVariable String name, @PathVariable String value, HttpServletRequest req) {
        HttpSession session = req.getSession();

        session.setAttribute(name, value);

        return "세션변수 %s의 값이 %s(으)로 설정되었습니다.".formatted(name, value);
    }

    @GetMapping("/getSession/{name}")
    @ResponseBody
    public String getSession(@PathVariable String name, HttpSession session) {
        String value = (String) session.getAttribute(name);

        return "세션변수 %s의 값이 %s 입니다.".formatted(name, value);
    }

    List<Article> list = new ArrayList<>();

    @GetMapping("/addArticle")
    @ResponseBody
    public String addArticle(String title, String body) {

        Article articleDto = new Article(title, body);
        list.add(articleDto);
        return "%d번 글이 등록되었습니다.".formatted(articleDto.getId());
    }

    @GetMapping("/getArticle/{id}")
    @ResponseBody
    public Article getArticle(@PathVariable int id) {

//        for(int i = 0 ; i < list.size() ; i++){
//            if( list.get(i).getId() == id){
//                //return Ut.json.toStr(list.get(i),"-");
//                return list.get(i);
//            }
//        }

        Article articleDto = list
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .get();

        //return "%d번 글이 없습니다.".formatted(id);
        return articleDto;
    }

    @GetMapping("/modifyArticle")
    @ResponseBody
    public String modifyArticle(int id, String title, String body) {

       for(int i = 0 ; i < list.size() ; i++){
            if( list.get(i).getId() == id){
                list.set(i, new Article(id, title,body));
                return "%d글이 수정되었습니다.".formatted(id);
            }
        }
        return "%d글이 수정되었습니다.".formatted(id);
    }

    @GetMapping("/deleteArticle")
    @ResponseBody
    public String deleteArticle(int id) {

        for(int i = 0 ; i < list.size() ; i++){
            if( list.get(i).getId() == id){
                list.remove(i);
                return "%d글이 삭제되었습니다.".formatted(id);
            }
        }
        return "%d글이 없습니다.".formatted(id);
    }

    List<Person> persons = new ArrayList<>();

    @GetMapping("/addPerson")
    @ResponseBody
    public String addPerson(@ModelAttribute Person person) {

        persons.add(person);
        return "%d, %s, %d가 추가 되었습니다.".formatted(person.getId(), person.getName(), person.getAge());
    }

    @GetMapping("/getPerson")
    @ResponseBody
    public Person getPerson(@ModelAttribute Person person) {


        return person;
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/question/list";
    }

}

@AllArgsConstructor
@Getter
@Setter
class Article {
    private static int lastId = 0;
    private int id;
    private String title;
    private String body;

    public Article(String title, String body) {
        this(++lastId, title, body);
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class Person {
    private int id;
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
