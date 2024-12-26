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
								"/idCheck",
								"/favicon.ico",
								"/images/**",
								"/js/**",
								"/css/**").permitAll()					//인증절차없이 접근
				.requestMatchers("/admin").hasRole("ADMIN")				//admin으로 인증될때만 접근가능
				.requestMatchers("/my/**").hasAnyRole("ADMIN","USER")	//ROLE_ADMIN /my로 시작하는 요청은 ADMIN과 USER 접속할 수 있음
				.anyRequest().authenticated());							//기타 다른 경로는 인증된 사용자만 접근 가능
		
		//Custom 로그인
		http.formLogin((auth) -> auth
				.loginPage("/login")				//로그인 화면 요청 / 로그인 없이 특정페이지에 접속을 시도하면 무조건 로그인 페이지로 리다이렉팅
				.loginProcessingUrl("/loginProc")	//로그인 처리 요청 / 로그인 화면에서 버튼을 클릭하면 시큐리티가 받아서 처리해주는 경로 / 서비스단으로 가서 서비스 처리(userdetails)
				.usernameParameter("userId")		//사용자가 재정의한 아이디 / controller 역할을 함 / 파라미터 Security가 사용하는 파라미터 대신 
				.passwordParameter("userPwd")		//사용자가 재정의한 비번 파라미터
				.defaultSuccessUrl("/")				//로그인 성공시
				.failureUrl("/login?error=true")	//로그인 실패시
				.permitAll());
		//세션은 아무나 들어올 수 없음
		
		//POST 요청 시 CSRF(Cross-Site request Forgery) 토근을 요청함
		http.csrf((auth) -> auth.disable());
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
