package com.cis.sys101_rest;

import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	public KeycloakClientRequestFactory keycloakClientRequestFactory;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// ClientHttpRequest request = keycloakClientRequestFactory.createRequest(arg0,
		// HttpMethod.GET);

	}

}
