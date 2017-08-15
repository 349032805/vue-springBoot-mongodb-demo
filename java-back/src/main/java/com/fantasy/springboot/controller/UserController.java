package com.fantasy.springboot.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.springboot.domain.User;
import com.fantasy.springboot.service.GlobalParams;
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
	public Object login(@RequestBody User user) {
		 User u = userService.findUserByUsername(user.getUsername());
		 Map<String, Object> map = new HashMap<>();
		 if(u == null){
			map.put(GlobalParams.SUCCESS, false);
			map.put(GlobalParams.ERROR_MSG,"无此用户!");
		 }else{
			 if(user.getPassword().equals(u.getPassword())){
				 map.put(GlobalParams.SUCCESS, true);
			 }else{
				map.put(GlobalParams.SUCCESS, false);
				map.put(GlobalParams.ERROR_MSG,"用户名或密码错误!");
			 }
			 
		 }
		 
		 return map;
		 
	}

	@RequestMapping(value = "/register", produces = "application/json", method = RequestMethod.POST)
	public Object register(@RequestBody User user) {
		userService.save(user);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, true);
		return map;
	}

}
