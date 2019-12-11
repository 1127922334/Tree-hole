package com.luntan.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//处理错误信息
@RequestMapping("${server.error.path:${error.path:/error}}")
public class fuxieErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error ";
    }
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, Model model){
        HttpStatus httpStatus = getStatus(request);
        if(httpStatus.is4xxClientError()){
            model.addAttribute("message","请求错误,请您换个姿势再访问");
        }
        if (httpStatus.is5xxServerError()){
                model.addAttribute("message","服务冒烟了要不你稍后再试试");
        }
        return  new ModelAndView("error");
    }

    private  HttpStatus getStatus(HttpServletRequest request){
            Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
            if (statusCode==null){
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
            try{
                return HttpStatus.valueOf(statusCode);
            }catch (Exception ex){
                    return  HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
