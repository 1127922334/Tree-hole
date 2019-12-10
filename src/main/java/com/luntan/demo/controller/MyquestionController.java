package com.luntan.demo.controller;

import com.luntan.demo.Service.QuestionService;
import com.luntan.demo.dto.PagesDTO;
import com.luntan.demo.mappers.QuestionMapper;
import com.luntan.demo.mappers.UserMapper;
import com.luntan.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyquestionController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionService questionService;

    @GetMapping("/profile/{action}")
    public  String getPerson(@PathVariable(name = "action") String action, Model model, HttpServletRequest request,
                             @RequestParam(name = "page",defaultValue = "1")Integer page,
                             @RequestParam(name = "size",defaultValue = "5") Integer size){
        Cookie[] cookies = request.getCookies();
        Users user1= (Users) request.getSession().getAttribute("user");
         //未登录则跳转回首页面
         if (user1==null) return "redirect:/";
        if (action.equals("Myquestions")){
           model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if (action.equals("repies")){
            model.addAttribute("section","repies");
            model.addAttribute("sectionName","最新回复");
        }
        PagesDTO pagesDTO = questionService.list(user1.getId(),page,size);
        model.addAttribute("mypage",pagesDTO);
        return "Person";
    }
}

