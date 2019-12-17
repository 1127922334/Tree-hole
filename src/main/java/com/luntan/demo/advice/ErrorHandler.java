package com.luntan.demo.advice;

import com.alibaba.fastjson.JSON;
import com.luntan.demo.dto.ResultDTO;
import com.luntan.demo.exception.DIYError;
import com.luntan.demo.exception.enum_ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

//全局异常处理
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    Object handle(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO;
            if (ex instanceof DIYError) {
                resultDTO =ResultDTO.errorOf((DIYError) ex);
            } else {
                resultDTO=ResultDTO.errorOf(enum_ErrorCode.SYS_ERROR);
            }
            try{
                response.setContentType("qpplication/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            }catch (Exception e){ }
            return  null;
        } else {
            //返回错误页面跳转
            if (ex instanceof DIYError) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message",enum_ErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
}

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
