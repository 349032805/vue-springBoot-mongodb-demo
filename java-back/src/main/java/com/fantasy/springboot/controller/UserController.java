package com.fantasy.springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.springboot.domain.User;
import com.fantasy.springboot.service.UserService;

/**
 * Created by fanjun on 2017/8/14.
 */
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index() {
		return "welcome mongodb.";
	}

	@RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
	public Boolean login(@RequestBody User user) {
		 User u = userService.findUserByUsername(user.getUsername());
		 if(u == null){
			 return false;
		 }else{
			 //继续写
			return null;
		 }
		 
	}

	@RequestMapping(value = "/add", produces = "application/json", method = RequestMethod.POST)
	public String save(@RequestBody User user) {
		userService.save(user);
		return "add successfully.";
	}

}
