package com.luntan.demo.controller;

import com.luntan.demo.dto.AcessTokenDTO;
import com.luntan.demo.dto.GithubUser;
import com.luntan.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public  String backindex(@RequestParam(name ="code") String code,@RequestParam(name = "state") String state){

        AcessTokenDTO acessTokenDTO = new AcessTokenDTO();
        acessTokenDTO.setCode(code);
        acessTokenDTO.setState(state);
        acessTokenDTO.setClient_id(clientid);
        acessTokenDTO.setClient_secret(clientsecret);
        acessTokenDTO.setRedirect_uri(Redirecturi);
        String AcessToken= githubProvider.getAccess(acessTokenDTO);
        GithubUser user =  githubProvider.getUser(AcessToken);
        System.out.println(user.getName());
        return "index";
    }


}
