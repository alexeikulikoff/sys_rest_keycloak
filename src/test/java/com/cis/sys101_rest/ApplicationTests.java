package com.cis.sys101_rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cis.sys101_rest.config.Token;
import com.cis.sys101_rest.controller.PrintTestController;
import com.google.gson.Gson;

@SpringBootTest(classes = Application.class, properties = "spring.main.allow-bean-definition-overriding=true")
class ApplicationTests {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;

	static final String TOKEN_PATH = "/auth/realms/spring-boot-quickstart/protocol/openid-connect/token";

	@Test
	void test_rest_with_access_token() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("localhost:8180").setPath(TOKEN_PATH);

		URI uri = builder.build();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(uri);
		httpPost.addHeader("Authorization", "Basic YXBwLWF1dGh6LXJlc3Qtc3ByaW5nYm9vdDpzZWNyZXQ=");
		httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");
		httpPost.setEntity(new StringEntity("username=alice&password=alice&grant_type=password"));

		CloseableHttpResponse response = (CloseableHttpResponse) httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity, "UTF-8");

		Gson gson = new Gson();
		Token token = gson.fromJson(json, Token.class);

		String url = "http://rest-server:8099/api/rest/name";
		String data = mockMvc
				.perform(MockMvcRequestBuilders.get(url).header("Authorization", "Bearer " + token.getAccess_token()))
				.andDo(MockMvcResultHandlers.print()).andExpect(status().isOk()).andReturn().getResponse()
				.getContentAsString();

		PrintTestController.print(data);

	}

	@Test
	void test_rest_without_access_token() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

		String url = "http://rest-server:8099/api/rest/name";
		String data = mockMvc.perform(MockMvcRequestBuilders.get(url)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().is(200)).andReturn().getResponse().getContentAsString();

		PrintTestController.print(data);
		int a = 3;
		assertEquals(1 + 2, a);
	}

}
