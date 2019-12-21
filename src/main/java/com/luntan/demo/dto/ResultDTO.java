package com.luntan.demo.dto;

import com.luntan.demo.exception.DIYError;
import com.luntan.demo.exception.ErrorCode;
import com.luntan.demo.exception.enum_ErrorCode;
import lombok.Data;

@Data
public class ResultDTO<T>{
    private  Integer code;
    private  String  message;
    private  T data;
    public static  ResultDTO errorOf(Integer code,String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return  resultDTO;
    }

    public static ResultDTO errorOf(enum_ErrorCode noLogin) {

        return errorOf(noLogin.getCode(),noLogin.getMessage());
    }
    public static ResultDTO errorOf(DIYError e) {
        return errorOf(e.getCode(),e.getMessage());
    }
    public static <T> ResultDTO okOf(T t){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return  resultDTO;
    }
}
