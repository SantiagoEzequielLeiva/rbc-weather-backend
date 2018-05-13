package com.sl.rbcweather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sl.rbcweather.model.User;
import com.sl.rbcweather.service.UserService;
import com.sl.rbcweather.util.RestResponse;

@CrossOrigin
@RestController
@RequestMapping({"/users"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> users() {
		return this.userService.list();
	}
	
	@PostMapping
	public RestResponse save(@RequestBody User user) {
		
		if ( !user.getUsername().equals(null) && !user.getUsername().equals("") ) {
			if ( this.userService.getByUsername(user.getUsername()) != null ) {
				return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Invalid username");
			} else {
				this.userService.save(user);
				
				return new RestResponse(HttpStatus.OK.value(), "The user has been saved");
			}
		} else {
			return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Not aceptable");
		}
	}
}
