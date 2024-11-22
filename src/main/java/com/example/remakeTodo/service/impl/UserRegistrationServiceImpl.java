package com.example.remakeTodo.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.remakeTodo.dto.SignupRequestDto;
import com.example.remakeTodo.entity.UserEntity;
import com.example.remakeTodo.service.UserRegistrationService;
import com.example.remakeTodo.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService{
	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
	
	
	@Override
	public void registerUser(SignupRequestDto userDto) throws Exception {
		// 二重登録のチェック
        if (userService.getUserByUsername(userDto.getUsername()) != null) {
            throw new Exception("ユーザ名が既に存在します。");
        }

        // パスワード一致のチェック
        if (!userDto.getPassword().equals(userDto.getPasswordConfirm())) {
            throw new Exception("パスワードと確認用パスワードが一致しません。");
        }

        // 新しいユーザーエンティティの作成と保存
        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        // パスワードをハッシュ化してセットする
        user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
        userService.createUser(user);
	}

}
