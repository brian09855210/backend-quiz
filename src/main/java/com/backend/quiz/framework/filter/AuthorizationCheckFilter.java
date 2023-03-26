package com.backend.quiz.framework.filter;

import com.backend.quiz.framework.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/25
 */
public class AuthorizationCheckFilter extends OncePerRequestFilter {

    private static final String LOGIN_URI = "/auth/login";
    private static final String LOGOUT_URI = "/auth/logout";
    private static final String USER_REGISTER_URI = "/user/register";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (!(LOGIN_URI.equals(request.getServletPath()) || LOGOUT_URI.equals(request.getServletPath()) || USER_REGISTER_URI.equals(request.getServletPath()))) {
            String token = request.getHeader("Authorization");
            if (!StringUtils.isBlank(token)) {
                try {
                    Map<String, Object> map = JwtUtil.parseToken(token);
                    String account = map.get("sub").toString();
                    // 取當下使用者比對 token account
                    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                    if (account.equals(userName)) {
                        chain.doFilter(request, response);
                    }
                } catch (Exception e) {
                    System.err.println("Error: " + e);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
