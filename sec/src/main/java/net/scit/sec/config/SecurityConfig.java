package net.scit.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration		//설정파일임을 나타태는 Annotation
@EnableWebSecurity	//스프링 시큐리티로 관리됨을 나타내는 Annotation
public class SecurityConfig {
	//요청 URL을 제어하는 메소드
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/",
								"/join",
								"/joinProc",
								"/images/**"
								,"/js/**"
								,"/css/**").permitAll()					//인증절차없이 접근
				.requestMatchers("/admin").hasRole("ADMIN")				//admin으로 인증될때만 접근가능
				.requestMatchers("/my/**").hasAnyRole("ADMIN","USER")	//ROLE_ADMIN /my로 시작하는 요청은 ADMIN과 USER 접속할 수 있음
				.anyRequest().authenticated());							//기타 다른 경로는 인증된 사용자만 접근 가능
		return http.build();
	}
	// 비밀번호 암호화
	//양방향 암호화(공개키 암호화)
	//단방향 암호화(Security에서 사용하는 암호화 방식)-비밀키 암호화
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
