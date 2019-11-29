package com.luntan.demo.controller;

import com.luntan.demo.mappers.UserMapper;
import com.luntan.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class GreetingController {
    @Autowired
    private UserMapper userMapper ;
   @GetMapping("/")
    public  String index(HttpServletRequest request){
       Cookie[] cookies = request.getCookies();
       if(cookies == null)  return "index";
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
       return "index";
   }

}