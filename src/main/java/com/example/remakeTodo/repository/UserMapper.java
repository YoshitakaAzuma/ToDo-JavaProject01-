package com.example.remakeTodo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.remakeTodo.entity.UserEntity;

@Mapper
public interface UserMapper {
	UserEntity findByUsername(String username);
	
	void insertUser(UserEntity userEntity);
}
