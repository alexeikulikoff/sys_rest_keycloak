package com.cis.sys101_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest/keycloak")
public class MainController {
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/name", method = RequestMethod.OPTIONS)
	public ResponseEntity<String> getOption() {
		System.out.println("get option to name:");
		return ResponseEntity.ok("");
	}

	@GetMapping("/name")
	public ResponseEntity<String> getName() {
		return ResponseEntity.ok("Bombero\n\n\n");
	}

	@GetMapping("/secondname")
	public ResponseEntity<String> getSecondname() {
		return ResponseEntity.ok("Archibaldo");
	}
}
