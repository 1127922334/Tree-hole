package com.luntan.demo.controller;

import com.luntan.demo.mappers.QuestionMapper;
import com.luntan.demo.mappers.UserMapper;
import com.luntan.demo.model.Question;
import com.luntan.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/publish")
    public  String publish(){
        return "publish";
    }

    @PostMapping("/publish")//从服务端传递数据到前端需要引入Model,将需要传递的数据写入Model
    public  String dopublish(@RequestParam("title")String title, @RequestParam("description")String description, @RequestParam("tag") String tag, HttpServletRequest request, Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        //判断
        if(title == null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","补充不能为空");
            return "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        Question question = new Question();
        Cookie[] cookies = request.getCookies();
        Users user1=null;
        //校验是否登录
        if(cookies == null)  return "publish";
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user1 = userMapper.findByToken(token);
                if(user1 != null){
                    request.getSession().setAttribute("user",user1);
                }
                break;
            }
        }
        //添加错误信息
            if(user1==null){
                model.addAttribute("error","用户未登录");
                return "publish";
            }
            //发布成功添加至数据库
        question.setTag(tag);
        question.setDescription(description);
        question.setTitle(title);
        question.setCreator(user1.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        questionMapper.Create(question);
        return "redirect:/";
    }
}
