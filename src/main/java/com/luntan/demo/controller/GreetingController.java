package com.luntan.demo.controller;

import com.luntan.demo.Service.QuestionService;
import com.luntan.demo.dto.PagesDTO;
import com.luntan.demo.dto.QuestionDTO;
import com.luntan.demo.mappers.UserMapper;
import com.luntan.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    private UserMapper userMapper ;
   @Autowired
   private QuestionService questionService;
    @GetMapping("/")
    public  String index(HttpServletRequest request,Model model,@RequestParam(name = "page",defaultValue = "1")Integer page,
                         @RequestParam(name = "size",defaultValue = "5")Integer size){
       Cookie[] cookies = request.getCookies();
        PagesDTO questionList = questionService.list(page, size);//使用Service层整合Mapper
       if(cookies == null){
           model.addAttribute("questions",questionList);//添加查询到的属性
           return "index";
       }
       //判断是否之前有登陆
        for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    Users user1 = userMapper.findByToken(token);
                    if(user1 != null){
                        request.getSession().setAttribute("user",user1);
                    }
                    break;
                }
        }

        model.addAttribute("questions",questionList);//添加查询到的属性
       return "index";
   }

}