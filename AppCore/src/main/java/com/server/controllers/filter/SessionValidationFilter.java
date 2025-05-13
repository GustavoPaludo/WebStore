package com.server.controllers.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.server.core.common.JwtUtil;
import com.server.core.user.UserService;
import com.server.core.user.model.UserAuthenticationReturn;
import com.server.core.user.model.UserFormModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SessionValidationFilter implements HandlerInterceptor {

	private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        if (path.contains("/authenticated/")) {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token JWT ausente");
                return false;
            }

            String token = authHeader.substring(7);

            try {
                Jws<Claims> claims = JwtUtil.validateToken(token);
                if(StringUtils.isBlank(claims.getBody().get("name").toString()) || StringUtils.isBlank(claims.getBody().get("email").toString()) || StringUtils.isBlank(claims.getBody().get("password").toString())) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token JWT inválido ou expirado");
                    return false;
                }

                UserFormModel userFormModel = new UserFormModel();
                userFormModel.setEmail(claims.getBody().get("email").toString());
                userFormModel.setPassword(claims.getBody().get("password").toString());

                UserAuthenticationReturn user = this.userService.login(userFormModel);

                if(user == null || user.getProblemList().hasAny()) {
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

    @Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
