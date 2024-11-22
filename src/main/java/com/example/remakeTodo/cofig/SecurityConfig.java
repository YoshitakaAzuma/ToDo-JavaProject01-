package com.example.remakeTodo.cofig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	// CustomAuthenticationProvider Beanをこのクラスに注入する
    private final CustomAuthenticationProvider customAuthenticationProvider;
	
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http
    	// カスタム認証プロバイダを設定
    	.authenticationProvider(customAuthenticationProvider)
    	// CORSの設定を適用
    	.cors(customizer -> customizer.configurationSource(corsConfigurationSource()))
    	// CSRFの保護を無効にする
    	.csrf(csrf -> csrf.disable())
    	.authorizeHttpRequests(authorizeRequests ->
    	authorizeRequests
    	// loginのパスへのリクエストはすべて許可
    	.requestMatchers("/login", "/signup","/registration").permitAll()
    	// その他のリクエストは認証が必要
    	.anyRequest().authenticated()
    			)
    	.formLogin(formLogin -> 
    	formLogin
    	// ログイン処理のURLを指定(フロントがログインボタン実行時にPOSTする場所)
    	.loginProcessingUrl("/authentication")
    	// カスタムログインページのURLを指定(Spring Securityデフォルトの画面を置き換える)
    	.loginPage("/login")
    	// ログイン成功時のリダイレクト先URLを指定
    	.defaultSuccessUrl("/")
    	// 認証失敗時のリダイレクト先URLを指定
    	.failureUrl("/login?error")
    			)
    	.logout(logout ->
    	logout
    	// ログアウト処理のURLを指定
    	.logoutUrl("/logout")
    	// ログアウト成功時のリダイレクト先URLを指定
    	.logoutSuccessUrl("/login?logout")
    			);

    	return http.build();
    }
	
	
	
	
	// @Beanをつけることで、このメソッドがSpringのコンテナにBeanとして登録される
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        // CORSの設定を行うためのオブジェクトを生成
        CorsConfiguration configuration = new CorsConfiguration();

        // クレデンシャル（資格情報（CookieやHTTP認証情報））を含むリクエストを許可する
        configuration.setAllowCredentials(true);

        // 許可するオリジン（この場合は"http://127.0.0.1:5500"のみ）を設定
        configuration.addAllowedOrigin("http://127.0.0.1:5500");

        // 任意のヘッダーを許可
        configuration.addAllowedHeader("*");

        // 任意のHTTPメソッド（GET, POSTなど）を許可
        configuration.addAllowedMethod("*");

        // CORS設定をURLベースで行うためのオブジェクトを生成
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // 全てのURLパスにこのCORS設定を適用
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
	
	
}
