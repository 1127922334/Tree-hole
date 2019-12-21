package com.luntan.demo.controller;

import com.luntan.demo.Service.QuestionService;
import com.luntan.demo.dto.PagesDTO;
import com.luntan.demo.dto.QuestionDTO;
import com.luntan.demo.mappers.QuestionMapperPlus;
import com.luntan.demo.mappers.UsersMapper;
import com.luntan.demo.model.Question;
import com.luntan.demo.model.Users;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {
   @Autowired
   private QuestionService questionService;
   @Autowired
   private QuestionMapperPlus questionMapperPlus;
   @Autowired
   private UsersMapper usersMapper;
    @RequestMapping("/")
    public  String index(HttpServletRequest request,Model model,@RequestParam(name = "page",defaultValue = "1")Integer page,
                         @RequestParam(name = "size",defaultValue = "5")Integer size){
        //查询浏览量最高的5个问题
             List<Question> hotquestion = questionMapperPlus.selectHot();
             List<QuestionDTO> questionDTOList = new ArrayList<>();
             for (Question question: hotquestion){
                 QuestionDTO questionDTO =new QuestionDTO();
                 BeanUtils.copyProperties(question,questionDTO);
                 Users users = usersMapper.selectByPrimaryKey(question.getCreator());
                 questionDTO.setUser(users);
                 questionDTOList.add(questionDTO);
             }


        PagesDTO questionList = questionService.list(page,size);//使用Service层整合Mapper
        model.addAttribute("hots",questionDTOList);
        model.addAttribute("questions",questionList);//添加查询到的属性
       return "index1";
   }

}