package com.beck.springbootmvcjpa.config;

import com.beck.springbootmvcjpa.interceptor.AdminInterceptor;
import com.beck.springbootmvcjpa.interceptor.AuthorizationInterceptor;
import com.beck.springbootmvcjpa.interceptor.DispatcherInterceptor;
import com.beck.springbootmvcjpa.interceptor.NonAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorsConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/product/*","/product/**");
        registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/user/reg, /user/auth");
        registry.addInterceptor(new NonAuthorizationInterceptor()).addPathPatterns("/basket","/basket/**","/user/out","/comment");
        registry.addInterceptor(new DispatcherInterceptor()).addPathPatterns("/orders","/orders/**");
    }
}
