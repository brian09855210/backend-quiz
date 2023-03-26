package com.backend.quiz.framework.config;

import com.backend.quiz.framework.filter.AuthorizationCheckFilter;
import com.backend.quiz.framework.filter.UserAuthFilter;
import com.backend.quiz.framework.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private AuthSuccessHandlerImpl authSuccessHandlerImpl;

	@Autowired
	private AuthFailHandlerImpl authFailHandlerImpl;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * API鑑權處理
	 *
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		UserAuthFilter loginFilter = new UserAuthFilter(authenticationManager());
		loginFilter.setAuthenticationSuccessHandler(authSuccessHandlerImpl);
		loginFilter.setAuthenticationFailureHandler(authFailHandlerImpl);
		loginFilter.setFilterProcessesUrl("/auth/login"); // 登入API URL

		LogoutFilter logoutFilter = new LogoutFilter("/#/login", new SecurityContextLogoutHandler());
		logoutFilter.setFilterProcessesUrl("/auth/logout");

		http
				// JWT filter
				.addFilterAfter(new AuthorizationCheckFilter(), BasicAuthenticationFilter.class)
				// 登入Filter
				.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
				// 登出Filter
				.addFilterAt(logoutFilter, LogoutFilter.class).exceptionHandling()
				// 登入驗證
				.authenticationEntryPoint(new AuthEntryPointImpl()).and().authorizeRequests()
				// 開放API
				.antMatchers("/user/register", "/auth/login").permitAll()
				// 有登入就能開啟
				.antMatchers("/*").authenticated()
				// 關閉CSRF驗證
				.and().csrf().disable();
	}

	/**
	 * 靜態資源不驗證，跳過的資源所有驗證功能都不可用
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				// 靜態資源跳過驗證
				.antMatchers(// Swagger UI
						"/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html",
						"/webjars/**", "/swagger-ui/**", "/v3/api-docs");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
}
