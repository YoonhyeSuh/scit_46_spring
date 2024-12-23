package net.scit.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration		//설정파일임을 나타태는 Annotation
@EnableWebSecurity	//스프링 시큐리티로 관리됨을 나타내는 Annotation
public class SecurityConfig {
	//요청 URL을 제어하는 메소드
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> auth.requestMatchers("/","/images/**").permitAll());
		return http.build();
	}
}
