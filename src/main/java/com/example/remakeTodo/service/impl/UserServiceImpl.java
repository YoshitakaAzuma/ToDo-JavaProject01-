package com.example.remakeTodo.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.remakeTodo.entity.UserEntity;
import com.example.remakeTodo.repository.UserMapper;
import com.example.remakeTodo.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserMapper mapper;

	@Override
	public UserEntity getUserByUsername(String username) {
		return mapper.findByUsername(username);
	}

	@Override
	public void createUser(UserEntity userEntity) {
		mapper.insertUser(userEntity);
		
	}
	
	// ログインしているユーザのIDを取得する
	/*@Override
	public int getCurrentUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null ) {
			System.out.println("NULLじゃない");
			if(authentication.getPrincipal() instanceof CustomUserDetails) {
				CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
	            return userDetails.getId();
			}
	    }
	    return 0; // 認証されていない場合や id が取得できない場合
	}*/
	
	@Override
	public String getCurrentUserName() {
		final String name = SecurityContextHolder.getContext().getAuthentication().getName();
		if(name == null) {
			System.out.println("取得失敗");
			return "Noname";
		}
		System.out.println("取得成功");
		return name;
	}

	@Override
	public int getCurrentUserId() {
		final String name = SecurityContextHolder.getContext().getAuthentication().getName();
		
		return mapper.findByUsername(name).getId();
	}



}
