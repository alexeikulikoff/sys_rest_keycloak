package com.cis.sys101_rest.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.cis.sys100_rest.dto.MenuNodeDto;
import com.cis.sys101_rest.config.FeignConfiguration;

@FeignClient(name = "rest_keycloak", configuration = FeignConfiguration.class)
public interface GuiControllerFeignClient {

	@GetMapping(path = "api/guicontroller/base/list")
	ResponseEntity<List<MenuNodeDto>> getBaseMenu();
}
