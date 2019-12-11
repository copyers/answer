package com.answer.question.Controller;

import com.answer.question.dto.PaginationDTO;
import com.answer.question.mapper.QuestionMapper;
import com.answer.question.mapper.UserMapper;
import com.answer.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Answerquestion {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size,
                        @RequestParam(name = "search", required = false) String search
                        ) {
        PaginationDTO pagination = questionService.list(search,page, size);
        model.addAttribute("pagination", pagination);
        if (null != search){
            model.addAttribute("search",search);
        }
        return "index";
    }


//    @ResponseBody
//    @GetMapping("/test/{id}")
//    public List<QuestionDTO> test(@PathVariable("id") Integer pagenum) {
//        PageHelper.startPage(pagenum, 3);
//        return questionMapper.selectbypagehelper();
//    }


}
