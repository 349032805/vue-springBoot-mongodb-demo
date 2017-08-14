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

	@RequestMapping("/load/{id}")
	public User load(@PathVariable("id") String id) {
		User user = new User();
		user.setId(id);
		return userService.find(user);
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		User user = new User();
		user.setId(id);
		userService.delete(user);
		return "delete successfully";
	}

	@RequestMapping("/list")
	public List<User> list() {
		return userService.findAll();
	}

	@RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.POST)
	public String update(@RequestBody User user) {
		userService.update(user);
		return userService.find(user).toString();
	}

	@RequestMapping(value = "/add", produces = "application/json", method = RequestMethod.POST)
	public String save(@RequestBody User user) {
		userService.save(user);
		return "add successfully.";
	}

}
