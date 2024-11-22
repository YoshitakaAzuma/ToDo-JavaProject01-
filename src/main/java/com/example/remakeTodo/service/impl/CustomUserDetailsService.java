package com.example.remakeTodo.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.remakeTodo.entity.UserEntity;
import com.example.remakeTodo.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserService userService;
	
	// ユーザー名でユーザーをロードするメソッド。
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// UserService を使ってユーザー情報をデータベースから取得する
		UserEntity userEntity = userService.getUserByUsername(username);
		
		if (userEntity == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

		return User.builder()
		        .username(userEntity.getUsername())
		        .password(userEntity.getPasswordHash())
		        .build();
	}

}
