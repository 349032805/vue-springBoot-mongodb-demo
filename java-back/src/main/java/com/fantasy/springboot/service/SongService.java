package com.fantasy.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasy.springboot.dao.SongDao;
import com.fantasy.springboot.domain.Song;

@Service
public class SongService {

	@Autowired
	private SongDao songDao;
	
	public void save(Song song) {
		songDao.save(song);
	}

	public Song findSongById(String id) {
		return songDao.findSongById(id);
	}

	public void delete(Song song) {
		songDao.delete(song);
	}
	
	public void update(Song song){
		songDao.update(song);
	}
	public List<Song> findAll() {
		return songDao.findAll();
	}
}
