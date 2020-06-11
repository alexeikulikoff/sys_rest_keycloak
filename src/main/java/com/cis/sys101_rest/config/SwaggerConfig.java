package com.cis.sys101_rest.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig extends WebMvcConfigurationSupport {

	private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

	private static final String OAUTH_NAME = "sys101_gui_controller";
	@Value("${keycloak.realm}")
	private String realm;

	@Value("${swagger.auth.token-url}")
	private String token_url;

	@Value("${swagger.auth.client-secret}")
	private String client_secret;

	@Value("${swagger.auth.client-id}")
	private String client_id;

	@Value("${swagger.auth.auth-url}")
	private String auth_url;

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
				.forPaths(PathSelectors.regex("/.*")).build();
	}

	@Bean
	public SecurityConfiguration security() {

		SecurityConfiguration conf = SecurityConfigurationBuilder.builder().realm(realm).clientId(client_id)
				.clientSecret(client_secret).appName("sys101_gui_controller").scopeSeparator(" ")
				.useBasicAuthenticationWithAccessCodeGrant(true).build();

		return conf;
	}

	private SecurityScheme securityScheme() {
		GrantType grantType = new AuthorizationCodeGrantBuilder()
				.tokenEndpoint(new TokenEndpoint(token_url, "access_token"))
				.tokenRequestEndpoint(new TokenRequestEndpoint(auth_url, client_id, client_secret)).build();

		SecurityScheme oauth = new OAuthBuilder().name("spring_oauth").grantTypes(Arrays.asList(grantType))
				.scopes(Arrays.asList(scopes())).build();
		return oauth;
	}

	private AuthorizationScope[] scopes() {
		// AuthorizationScope[] scopes = { new AuthorizationScope("OAuth2", "OAuth2"),
		// new AuthorizationScope("accessCode", "accessCode") };
		AuthorizationScope[] scopes = {};
		return scopes;
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title(SWAGGER_TITLE).description(SWAGGER_DESCRIPTION)
				.contact(new Contact("КИ Системы", "http://c-i-systems.com", "info@c-i-systems.com")).build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

	}

	public static final String SWAGGER_TITLE = "Меню ";
	public static final String SWAGGER_DESCRIPTION = "Меню ";
}
