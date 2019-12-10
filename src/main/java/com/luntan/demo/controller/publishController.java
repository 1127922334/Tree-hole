package com.luntan.demo.controller;

import com.luntan.demo.Service.QuestionService;
import com.luntan.demo.mappers.QuestionMapper;
import com.luntan.demo.mappers.UserMapper;
import com.luntan.demo.model.Question;
import com.luntan.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class publishController {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserMapper userMapper;
    @GetMapping("/publish/{id}")
    public  String edit(@PathVariable(name = "id") Integer id,Model model){
       Question question =  questionMapper.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish1";
    }

    @GetMapping("/publish")
    public  String publish(){
        return "publish1";
    }

    @PostMapping("/publish")//从服务端传递数据到前端需要引入Model,将需要传递的数据写入Model
    public  String dopublish(@RequestParam("title")String title,
                             @RequestParam("description")String description,
                             @RequestParam("tag") String tag,
            @RequestParam("id") Integer id, HttpServletRequest request, Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        //判断
        if(title == null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish1";
        }
        if(description==null||description==""){
            model.addAttribute("error","补充不能为空");
            return "publish1";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish1";
        }

        Question question = new Question();
        Users user1= (Users) request.getSession().getAttribute("user");
        //添加错误信息
            if(user1==null){
                model.addAttribute("error","用户未登录");
                return "publish1";
            }
            //发布成功添加至数据库
        question.setTag(tag);
        question.setDescription(description);
        question.setTitle(title);
        question.setCreator(user1.getId());
        question.setId(id);
        question.setGmtcreate(System.currentTimeMillis());
        question.setGmtmodified(question.getGmtcreate());
        questionService.CreateUpdate(question);
        return "redirect:/";
    }
}
