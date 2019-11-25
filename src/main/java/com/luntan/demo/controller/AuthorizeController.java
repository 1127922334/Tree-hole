package com.luntan.demo.controller;

import com.luntan.demo.dto.AcessTokenDTO;
import com.luntan.demo.dto.GithubUser;
import com.luntan.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class AuthorizeController {
    @Value("${github.client_id}")
    private String clientid;
    @Value("${github.client_secret}")
    private String clientsecret;
    @Value("${github.Redirect_uri}")
    private String Redirecturi;


    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/gitlogin")
    public  String backindex(@RequestParam(name ="code") String code, @RequestParam(name = "state") String state, HttpServletRequest request){
        AcessTokenDTO acessTokenDTO = new AcessTokenDTO();
        acessTokenDTO.setCode(code);
        acessTokenDTO.setState(state);
        acessTokenDTO.setClient_id(clientid);
        acessTokenDTO.setClient_secret(clientsecret);
        acessTokenDTO.setRedirect_uri(Redirecturi);
        String AcessToken= githubProvider.getAccess(acessTokenDTO);
        GithubUser user =  githubProvider.getUser(AcessToken);
        if(user!=null){
            //登录成功
            request.getSession().setAttribute("user",user);//从request获取Session,添加登录消息
            return "redirect:/";//重定向页面
        }else {
            //登录失败
            return "redirect:/";//重定向页面
        }
    }


}
