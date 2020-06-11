package com.cis.sys101_rest.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/**").addResourceLocations("/", "classpath:/templates/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");

	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(httpMessageConverter());
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(exceptionHandlerExceptionResolver());
	}

	@Bean
	public HttpMessageConverter<String> httpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
		return converter;
	}

	@Bean
	public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
		ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
		List<HttpMessageConverter<?>> ls = new ArrayList<>();
		ls.add(mappingJackson2HttpMessageConverter());
		resolver.setMessageConverters(ls);
		return resolver;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		List<MediaType> types = new ArrayList<>();

		types.add(MediaType.valueOf("text/plain;charset=UTF-8"));
		types.add(MediaType.valueOf("text/html;charset=UTF-8"));
		types.add(MediaType.valueOf("application/json;charset=UTF-8"));
		types.add(MediaType.valueOf("application/x-www-form-urlencoded;charset=UTF-8"));
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();

		jsonConverter.setSupportedMediaTypes(types);

		return jsonConverter;
	}

}
