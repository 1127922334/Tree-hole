package com.luntan.demo.controller;

import com.luntan.demo.dto.AcessTokenDTO;
import com.luntan.demo.dto.GithubUser;
import com.luntan.demo.mappers.UserMapper;
import com.luntan.demo.model.Users;
import com.luntan.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Value("${github.client_id}")
    private String clientid;
    @Value("${github.client_secret}")
    private String clientsecret;
    @Value("${github.Redirect_uri}")
    private String Redirecturi;

    @Autowired
    private UserMapper userMapper;

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
        String AcessToken= githubProvider.getAccess(acessTokenDTO);//获取accesstoken
        GithubUser user =  githubProvider.getUser(AcessToken);//获取User数据
        if(user!=null){
            //登录成功
           Users users  = new Users();
           users.setToken(UUID.randomUUID().toString());
           users.setName(user.getName());
           users.setAccountId(String.valueOf(user.getId()));//将int类型强转为String
           users.setAvatarUrl(user.getAvatar_url());
           users.setGmtCreate(System.currentTimeMillis());
           users.setGmtModified(users.getGmtCreate());
           userMapper.insert(users);
            request.getSession().setAttribute("user",user);//从request获取Session,添加登录消息
            return "redirect:/";//重定向页面
        }else {
            //登录失败
            return "redirect:/";//重定向页面
        }
    }


}
