package com.answer.question.Controller;

import com.answer.question.dto.PaginationDTO;
import com.answer.question.dto.QuestionDTO;
import com.answer.question.mapper.QuestionMapper;
import com.answer.question.mapper.UserMapper;
import com.answer.question.service.QuestionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Answerquestion {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size) {
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }


//    @ResponseBody
//    @GetMapping("/test/{id}")
//    public List<QuestionDTO> test(@PathVariable("id") Integer pagenum) {
//        PageHelper.startPage(pagenum, 3);
//        return questionMapper.selectbypagehelper();
//    }


}
