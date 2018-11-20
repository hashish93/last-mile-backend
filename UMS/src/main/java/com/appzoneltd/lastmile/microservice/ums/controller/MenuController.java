package com.appzoneltd.lastmile.microservice.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.ums.dto.MenuDto;
import com.appzoneltd.lastmile.microservice.ums.model.Parameter;
import com.appzoneltd.lastmile.microservice.ums.service.MenuService;

@RequestMapping("/menu")
@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/getByUserId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getByUserId(@RequestBody Parameter parameter) {
		if (parameter.getUserId() != null) {
			List<MenuDto> menuDtos = menuService.getAllUserMenus(parameter.getUserId());
			if (menuDtos.isEmpty()) {
				Message message = new Message(MessageType.ERROR, "No Menu", "Has No Roles");
				return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<List<MenuDto>>(menuDtos, HttpStatus.OK);
			}
		}
		Message message = new Message(MessageType.ERROR, "User", "UserId not found");
		return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
	}

}
