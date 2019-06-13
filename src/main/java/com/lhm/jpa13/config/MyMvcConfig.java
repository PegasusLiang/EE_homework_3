package com.lhm.jpa13.config;

import com.lhm.jpa13.component.LoginHandlerIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("register").setViewName("register");
        registry.addViewController("login").setViewName("login");
        registry.addViewController("main.html").setViewName("index");

    }

//    //注册拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //静态资源， *css,*js
//        registry.addInterceptor(new LoginHandlerIntercepter()).addPathPatterns("/**")
//                .excludePathPatterns("/index.html","/","/user/login","/login.html");
//    }
}
