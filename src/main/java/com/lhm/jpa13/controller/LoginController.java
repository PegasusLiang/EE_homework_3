package com.lhm.jpa13.controller;

import com.lhm.jpa13.entity.UserEntity;
import com.lhm.jpa13.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserJPA userJPA;


    @RequestMapping("/test")
    @ResponseBody
    public Map<String, String> test(){
        Map<String,String> map = new HashMap<>();
        map.put("key","key");
        return map;
    }

    //实现register功能
    @PostMapping("/user/register")
    public String regsiter(UserEntity user){
        userJPA.save(user);//UserRepository自带简单的sql语句，即insert into...
        return "login";
    }

    //实现login功能
    @PostMapping("/user/login")
    public String login(@RequestParam("username")String username,
                              @RequestParam("password")String password,
                            HttpSession session, Map<String,Object> map) {
        UserEntity user = userJPA.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            return "redirect:/main.html";//redirect重定向
        } else {
            //向页面输出的内容
            map.put("msg", "用户名或密码错误");
            return "login";
        }
    }



}
