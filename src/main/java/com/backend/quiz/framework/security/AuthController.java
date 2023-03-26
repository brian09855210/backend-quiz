package com.backend.quiz.framework.security;

import com.backend.quiz.vo.req.SystemUserLoginRequest;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/24
 */
@Api(tags = "login", description = "登入驗證")
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private HttpServletRequest request;

	@Bean
	public RequestContextListener requestContextListener(){
		return new RequestContextListener();
	}

	/**
	 * Implemented by Spring Security
	 */
	@ApiOperation(value = "登入驗證")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "成功"),
			@ApiResponse(code = 401, message = "帳號密碼錯誤"),
			@ApiResponse(code = 500, message = "系統錯誤")
	})
	@PostMapping("/login")
	public void login(@RequestBody @ApiParam(value = "登入資訊", required = true) SystemUserLoginRequest systemUserLoginRequest) {
		throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
	}
}
