package com.fantasy.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	

	@RequestMapping("/getSongDetail/{id}")
	public Song getSongById(@PathVariable("id") String id) {
		return songService.findSongById(id);
	}

	@RequestMapping(value = "/deleteSong", produces = "application/json", method = RequestMethod.POST)
	public Object deleteSong(@RequestBody String id) {
		songService.delete(id);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, false);
		return map;
	}

	@RequestMapping("/getAllSongs")
	public List<Song> getAllSongs() {
		return songService.findAll();
	}

	@RequestMapping(value = "/updateSong", produces = "application/json", method = RequestMethod.POST)
	public Object update(@RequestBody Song song) {
		songService.update(song);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, false);
		return map;
	}

	@RequestMapping(value = "/addSong", produces = "application/json", method = RequestMethod.POST)
	public Object addSong(@RequestBody Song song) {
		songService.save(song);
		Map<String, Object> map = new HashMap<>();
		map.put(GlobalParams.SUCCESS, false);
		return map;
	}
}
