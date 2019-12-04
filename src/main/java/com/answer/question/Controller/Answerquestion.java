package com.answer.question.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Answerquestion {

    @GetMapping("/")
    public String index(){
        return "index";
    }

}
