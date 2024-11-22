package com.example.remakeTodo.service;

import com.example.remakeTodo.entity.UserEntity;

public interface UserService {
	
	UserEntity getUserByUsername(String username);
	
	void createUser(UserEntity userEntity);
	
	int getCurrentUserId();
	
	String getCurrentUserName();
}
