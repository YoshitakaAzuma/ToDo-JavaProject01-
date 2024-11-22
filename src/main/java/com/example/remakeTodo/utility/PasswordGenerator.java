package com.example.remakeTodo.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
	public static void main(String[] args) {
		// 「BCrypt」のインスタンス化
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// 入力値
		String password = "adminpassword";
		String password1 = "user1password";
		String password2 = "user2password";
		// パスワードをハッシュ化
		String encodedPassword = encoder.encode(password);
		String encodedPassword1 = encoder.encode(password1);
		String encodedPassword2 = encoder.encode(password2);
		// 表示
		System.out.println("adminパスワード: " + encodedPassword);
		System.out.println("user1パスワード: " + encodedPassword1);
		System.out.println("user2パスワード: " + encodedPassword2);
		}
}
