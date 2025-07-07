package com.dominio.springradio1.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AutorizadorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String url = request.getRequestURI();
        System.out.println("Url do interceptador "+url);


        if (url.equals("/") || url.equals("/login")) {
            return true;
        }

        if (request.getSession().getAttribute("user") == null) {
            //System.out.println("usuario não está logado");
            response.sendRedirect("/login");
            return false;
        }

        System.out.println("Passamos a verificacao");

        return true;
    }
}
