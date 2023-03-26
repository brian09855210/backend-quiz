package com.backend.quiz.framework.security;

import com.backend.quiz.entity.SystemUserEntity;
import com.backend.quiz.framework.util.JwtUtil;
import com.backend.quiz.repository.SystemUserRepository;
import com.backend.quiz.vo.resp.SystemUserLoginResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
@Component
public class AuthSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private static final String AUTH_HEADER = "Authorization";

	@Autowired
	private SystemUserRepository systemUserRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String account = request.getAttribute("account").toString();
		SystemUserEntity userInfo = systemUserRepository.findByAccount(account);
		SystemUserLoginResponse systemUserLoginResponse = new SystemUserLoginResponse();
		systemUserLoginResponse.setAccount(userInfo.getAccount());
		systemUserLoginResponse.setName(userInfo.getName());
		String jwtToken = JwtUtil.createToken(account);
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.setHeader(AUTH_HEADER, jwtToken);
		response.getWriter().write(new Gson().toJson(systemUserLoginResponse));
	}
}
