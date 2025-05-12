package com.server.controllers.filter;

import org.springframework.web.servlet.HandlerInterceptor;

import com.server.core.common.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionValidationFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        if (path.contains("/authenticated/")) {
            HttpSession session = request.getSession(false);
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token JWT ausente");
                return false;
            }

            if (session == null || session.getAttribute("userEmail") == null || session.getAttribute("userName") == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Sessão inválida ou expirada");
                return false;
            }

            String userName = session.getAttribute("userName").toString();
            String userEmail = session.getAttribute("userEmail").toString();

            String token = authHeader.substring(7);

            try {
                Jws<Claims> claims = JwtUtil.validateToken(token);
                if(!userName.equals(claims.getBody().get("name")) || !userEmail.equals(claims.getBody().get("email"))) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token JWT inválido ou expirado");
                    return false;
                }
            } catch (JwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token JWT inválido ou expirado");
                return false;
            }
        }

        return true;
    }
}
