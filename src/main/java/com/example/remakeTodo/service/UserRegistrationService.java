package com.example.remakeTodo.service;

import com.example.remakeTodo.dto.SignupRequestDto;

public interface UserRegistrationService {
	void registerUser(SignupRequestDto userDto)throws Exception;
}
