package com.answer.question;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@MapperScan(basePackages = "com.answer.question.mapper")
public class AnswerquestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnswerquestionApplication.class, args);
    }

}
