package com.example.remakeTodo.cofig;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// ブラウザから入力したユーザ名・パスワードを取得
        String username = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        if (passwordEncoder.matches(inputPassword, userDetails.getPassword())) {
            // 認証成功時は、認証トークン(ユーザ名、パスワード、権限)を作成
            return new UsernamePasswordAuthenticationToken(userDetails, inputPassword, userDetails.getAuthorities());
        } else {
            // 認証失敗は、エラーを返す
            throw new BadCredentialsException("Authentication failed");
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 // authentication(認証方式)がUsernamePasswordAuthenticationToken.class(ユーザー名とパスワード認証)か判定
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
