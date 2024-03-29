package com.luntan.demo.interceptors;

import com.luntan.demo.mappers.UsersMapper;
import com.luntan.demo.model.Users;
import com.luntan.demo.model.UsersExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//拦截器类
@Service
public class SessionInterceptors implements HandlerInterceptor {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length!=0){
            //判断是否之前有登陆
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    UsersExample usersExample = new UsersExample();
                    usersExample.createCriteria().andTokenEqualTo(token);
                    List<Users> user1 = usersMapper.selectByExample(usersExample);
                        if(user1.size() != 0){
                            request.getSession().setAttribute("user",user1.get(0));
                        }
                        break;
                    }

                }
            }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
