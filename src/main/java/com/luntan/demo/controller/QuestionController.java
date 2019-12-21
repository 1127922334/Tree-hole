package com.luntan.demo.controller;

import com.luntan.demo.Service.CommentService;
import com.luntan.demo.Service.QuestionService;
import com.luntan.demo.dto.CommentDTO;
import com.luntan.demo.dto.QuestionDTO;
import com.luntan.demo.enums.CommentTypeEnums;
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
    public  String question (@PathVariable(name="id") Long id , Model model){
       QuestionDTO questionDTO = questionService.getById(id);
       //相关问题
       List<QuestionDTO> relatedDTOList = questionService.selectRelated(questionDTO);
       //查询评论
       List<CommentDTO> comments = commentService.listBytargetId(id, CommentTypeEnums.QUESTION);
       //累加阅读数
       questionService.incView(id);
       model.addAttribute("tags",relatedDTOList);
       model.addAttribute("question",questionDTO);
       model.addAttribute("comments",comments);
        return "question";
    }
}
