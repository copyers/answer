package com.answer.question.Controller;

import com.answer.question.dto.CommentCreateDTO;
import com.answer.question.dto.CommentDTO;
import com.answer.question.dto.QuestionDTO;
import com.answer.question.enums.CommentTypeEnum;
import com.answer.question.service.CommentService;
import com.answer.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){


        QuestionDTO questionDTO = questionService.getById(id);

        List<CommentDTO> comments =  commentService.listByQuestionId(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.inView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",comments);
        return "question";

    }

}
