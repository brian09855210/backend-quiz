package com.backend.quiz.framework.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
public class UserAuthFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public UserAuthFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		String account = null;
		String password = null;
		// 判斷是否以json形式傳遞引數
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			try {
				@SuppressWarnings("unchecked")
				Map<String, String> maps = new ObjectMapper().readValue(request.getInputStream(), Map.class);
				account = maps.get("account");
				password = maps.get("password");
			} catch (IOException e) {
				e.printStackTrace();
			}
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(account, password);
			setDetails(request, token);
			//帶account給後方使用
			request.setAttribute("account", account);
			return authenticationManager.authenticate(token);
		}

		// 否則使用原來的key-value傳遞引數
		return super.attemptAuthentication(request, response);
	}
}
