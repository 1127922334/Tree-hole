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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
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
    public  String backindex(@RequestParam(name ="code") String code, @RequestParam(name = "state") String state,HttpServletRequest request, HttpServletResponse response){
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
           Users users1 = userMapper.findByAccount_id(String.valueOf(user.getId()));
            if(users1==null){
                Users users  = new Users();
                String token =UUID.randomUUID().toString();
                users.setToken(token);
                users.setName(user.getName());
                users.setAccount_Id(String.valueOf(user.getId()));//将int类型强转为String
                users.setAvatarUrl(user.getAvatar_url());
                users.setGmtCreate(System.currentTimeMillis());
                users.setGmtModified(users.getGmtCreate());
                users.setBio(user.getBio());
                userMapper.insert(users);
                Cookie cookie =new Cookie("token",token);
                cookie.setPath("/");
                int time = 60*60*24;//给Cookie设置过期时间,防止浏览器关闭Cookie被删除
                cookie.setMaxAge(time);
                response.addCookie(cookie);//添加Cookie

            }else {
                Cookie cookie =new Cookie("token",users1.getToken());
                cookie.setPath("/");
                int time = 60*60*24;
                cookie.setMaxAge(time);
                response.addCookie(cookie);

            }

            return "redirect:/";//重定向页面
        }else {
            //登录失败
            return "redirect:/";//重定向页面
        }
    }


}
