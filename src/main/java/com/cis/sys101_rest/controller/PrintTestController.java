package com.cis.sys101_rest.controller;

import org.springframework.stereotype.Controller;

@Controller
public class PrintTestController {
	public static void print(String input) {
		System.out.println(input);
	}
}
