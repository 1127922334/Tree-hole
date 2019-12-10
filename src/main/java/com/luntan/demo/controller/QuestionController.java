package com.luntan.demo.controller;

import com.luntan.demo.Service.QuestionService;
import com.luntan.demo.dto.QuestionDTO;
import com.luntan.demo.mappers.QuestionMapper;
import com.luntan.demo.mappers.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public  String question (@PathVariable(name="id") Integer id , Model model){
       QuestionDTO questionDTO = questionService.getById(id);
       model.addAttribute("question",questionDTO);
        return "question";
    }
}
