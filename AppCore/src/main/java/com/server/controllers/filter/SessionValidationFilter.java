package com.server.controllers.filter;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionValidationFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        if (path.contains("/authenticated/")) {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("userEmail") == null || session.getAttribute("userName") == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Sessão inválida ou expirada");
                return false;
            }
        }

        return true;
    }
}
