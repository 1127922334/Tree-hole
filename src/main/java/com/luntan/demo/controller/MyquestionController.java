package com.luntan.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyquestionController {
    @GetMapping("/profile/{action}")
    public  String getPerson(@PathVariable(name = "action") String action, Model model){
        if (action.equals("Myquestions")){
           model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if (action.equals("repies")){
            model.addAttribute("section","repies");
            model.addAttribute("sectionName","最新回复");
        }
        return "Person";
    }
}

