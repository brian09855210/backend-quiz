package com.backend.quiz.framework.config;

import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description:
 * @Author: brian.chang
 * @Date: 2023/3/23
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

	/**
	 * Swagger UI Url http://localhost:8081/swagger-ui/index.html
	 */

	public static final String AUTHORIZATION_HEADER = "Authorization";

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.OAS_30)
				// Response 不使用 Swagger 預設 Http 狀態訊息
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
				.securityContexts(securityContext())
				.securitySchemes(securityScheme())
				// 設定 IP && port
				// .host("127.0.0.1:8081")
				// 首頁右上方 spec 名
				.groupName("backend-quiz")
				// 是否啟用 Swagger，如果 false 的話，swagger 服務將關閉，默認是 true
				.enable(true).select()
				/**
				 * apis():指定掃描的介面 RequestHandlerSelectors:設定要掃描介面的方式 basePackage:指定要掃描的包
				 * any:掃面全部 none:不掃描 withClassAnnotation:掃描類上的註解(引數是類上註解的class物件)
				 * withMethodAnnotation:掃描方法上的註解(引數是方法上的註解的class物件)
				 */
				// 掃描 Controller 有加上 @Api 註解的 Class
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))

				// 掃描 Controller Method 有加上 @ApiOperation 註解的 Class
				// .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))

				// 掃描單一 package
				// .apis(RequestHandlerSelectors.basePackage(""))

				// 排除
				//.apis(Predicate.not(RequestHandlerSelectors.basePackage("")))

				/**
				 * paths():過濾路徑 PathSelectors:設定過濾的路徑 any:過濾全部路徑 none:不過濾路徑
				 * ant:過濾指定路徑:按照按照Spring的AntPathMatcher提供的match方法進行匹配
				 * regex:過濾指定路徑:按照String的matches方法進行匹配
				 */
				.paths(PathSelectors.any()).build();
	}

	// 設定 Swagger 基本資訊
	private ApiInfo apiInfo() {
		Contact contact = new Contact("", "", "");

		return new ApiInfoBuilder()
				// 文件標題
				.title("Backend Quiz Swagger API Docs")
				// 文件描述
				.description("後端面試題目文件")
				// 聯絡人
				.contact(contact)
				// 版本號
				.version("1.0")
				// 更新此API的許可證資訊
				// .license("")
				// 更新此API的許可證Url
				// .licenseUrl("")
				// 更新服務條款URL
				// .termsOfServiceUrl("")
				.build();
	}
	private List<SecurityScheme> securityScheme() {
		return Collections.singletonList(new ApiKey("Authorization", AUTHORIZATION_HEADER, "header"));
	}

	private List<SecurityContext> securityContext() {
		return Collections.singletonList(SecurityContext.builder().securityReferences(defaultAuth()).build());
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
	}
}
