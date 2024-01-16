package com.beck.springbootmvcjpa.interceptor;

import com.beck.springbootmvcjpa.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        User currentUser = (User) request.getSession().getAttribute("currentUser");

        if (currentUser == null || !currentUser.isAdmin()){
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
