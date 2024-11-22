package com.example.remakeTodo.controller;

import java.net.URI;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.remakeTodo.dto.SignupRequestDto;
import com.example.remakeTodo.service.UserRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserRegistrationService userRegistrationService;
    private static final String BASE_FRONTEND_URL = "http://localhost:8080";
	
    
    // ユーザー登録のエンドポイント
    @PostMapping("/registration")
    public ResponseEntity<String> registration(@Valid @ModelAttribute SignupRequestDto signupRequestDto,
            BindingResult bindingResult) {

        // バリデーションエラーのチェック
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return redirectToErrorPage(errorMessage);
        }

        try {
            // ユーザー登録処理
            userRegistrationService.registerUser(signupRequestDto);
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(BASE_FRONTEND_URL + "/"))
                    .body("User registered successfully.");
        } catch (DataIntegrityViolationException ex) { // データベースの一意制約違反のハンドリング
            String errorMessage = "メールアドレスが既に存在します";
            return redirectToErrorPage(errorMessage);
        } catch (Exception ex) {
            // 登録エラーのハンドリング
            return redirectToErrorPage(ex.getMessage());
        }
    }

    // エラーメッセージを持ったエラーページへのリダイレクト
    private ResponseEntity<String> redirectToErrorPage(String errorMessage) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(BASE_FRONTEND_URL + "/signup")
                .queryParam("error", errorMessage);

        URI errorPageUri = builder.build().encode().toUri();
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(errorPageUri)
                .body(errorMessage);
    }
	
	
	
	
	
	
	
	
	
	
}
