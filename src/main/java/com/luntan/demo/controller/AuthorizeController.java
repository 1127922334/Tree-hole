package com.luntan.demo.controller;

import com.luntan.demo.dto.AcessTokenDTO;
import com.luntan.demo.dto.GithubUser;
import com.luntan.demo.mappers.UsersMapper;
import com.luntan.demo.model.Users;
import com.luntan.demo.model.UsersExample;
import com.luntan.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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
    private UsersMapper usersMapper;

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
            UsersExample usersExample = new UsersExample();
            usersExample.createCriteria().andAccountIdEqualTo(String.valueOf(user.getId()));
            List<Users> users1 = usersMapper.selectByExample(usersExample);
            if(users1.size()==0){
                Users users  = new Users();
                String token =UUID.randomUUID().toString();
                users.setToken(token);
                users.setName(user.getName());
                users.setAccountId(String.valueOf(user.getId()));//将int类型强转为String
                users.setAvatarUrl(user.getAvatar_url());
                users.setGmtCreate(System.currentTimeMillis());
                users.setGmtModified(users.getGmtCreate());
                users.setBio(user.getBio());
                usersMapper.insert(users);
                Cookie cookie =new Cookie("token",token);
                cookie.setPath("/");
                int time = 60*60*24;//给Cookie设置过期时间,防止浏览器关闭Cookie被删除
                cookie.setMaxAge(time);
                response.addCookie(cookie);//添加Cookie

            }else {
                Users user_s =new Users();
                String token =UUID.randomUUID().toString();
                user_s.setToken(token);
                user_s.setName(user.getName());
                user_s.setAccountId(String.valueOf(user.getId()));//将int类型强转为String
                user_s.setAvatarUrl(user.getAvatar_url());
                user_s.setGmtModified(System.currentTimeMillis());
                user_s.setBio(user.getBio());
                UsersExample usersExample1 = new UsersExample();
                usersExample1.createCriteria().andAccountIdEqualTo(user_s.getAccountId());
                    usersMapper.updateByExampleSelective(user_s,usersExample1);
                Cookie cookie =new Cookie("token",user_s.getToken());
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
    @GetMapping("/logout")
    public String    logout(HttpServletRequest request,HttpServletResponse response ){
            request.getSession().removeAttribute("user");
            Cookie cookie = new Cookie("token",null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        return "redirect:/";
    }

}
