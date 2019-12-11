package com.luntan.demo.controller;

import com.luntan.demo.Service.QuestionService;
import com.luntan.demo.dto.PagesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GreetingController {
   @Autowired
   private QuestionService questionService;

    @RequestMapping("/")
    public  String index(HttpServletRequest request,Model model,@RequestParam(name = "page",defaultValue = "1")Integer page,
                         @RequestParam(name = "size",defaultValue = "5")Integer size){

        PagesDTO questionList = questionService.list(page,size);//使用Service层整合Mapper
        model.addAttribute("questions",questionList);//添加查询到的属性
       return "index1";
   }

}