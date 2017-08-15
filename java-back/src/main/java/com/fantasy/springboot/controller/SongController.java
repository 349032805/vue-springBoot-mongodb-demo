package com.fantasy.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.springboot.domain.Song;
import com.fantasy.springboot.service.GlobalParams;
import com.fantasy.springboot.service.SongService;

@RestController
@RequestMapping("/api")
public class SongController {

	@Autowired
	private SongService songService;
	
	@Autowired
	private GlobalParams globalParams;


	@RequestMapping("/load/{id}")
	public Song getSongById(@PathVariable("id") String id) {
		return songService.findSongById(id);
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		User user = new User();
		user.setId(id);
		userService.delete(user);
		return "delete successfully";
	}

	@RequestMapping("/list")
	public List<Song> list() {
		return songService.findAll();
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
