package com.fantasy.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.fantasy.springboot.domain.User;

/**
 * Created by fanjun on 2017/8/14.
 */
@Repository
public class UserDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(User user) {
		//因为是练习项目,密码就不MD5加密了,实际开发中需要加密保存数据库
		mongoTemplate.save(user);
	}
		
	//通过用户名查找用户
	public User findUserByUsername(String username) {
		return mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), User.class);
	}
	
	public void update(User user){
		Query query=new Query(Criteria.where("id").is(user.getId()));
		Update update=new Update().set("username", user.getUsername()).set("password", user.getPassword());
		mongoTemplate.updateFirst(query, update, User.class);
	}

	public void delete(User user) {
		mongoTemplate.remove(new Query(Criteria.where("id").is(user.getId())),User.class);
	}
	
	public List<User> findAll() {
		return mongoTemplate.findAll(User.class);
	}
}
