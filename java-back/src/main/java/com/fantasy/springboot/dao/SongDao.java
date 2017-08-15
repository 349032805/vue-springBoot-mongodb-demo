package com.fantasy.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.fantasy.springboot.domain.Song;

@Repository
public class SongDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(Song song) {
		mongoTemplate.save(song);
	}

	public Song findSongById(String id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), Song.class);
	}

	public void update(Song song) {
		Query query = new Query(Criteria.where("id").is(song.getId()));
		Update update = new Update()
				.set("song_name", song.getSongName())
				.set("singer", song.getSinger())
				.set("update_at",song.getUpdateAt());
		mongoTemplate.updateFirst(query, update, Song.class);
	}

	public void delete(String id) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(id)), Song.class);
	}

	public List<Song> findAll() {
		return mongoTemplate.findAll(Song.class);
	}
}
