package net.scit.spring7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;
import net.scit.spring7.handler.LoginFailureHandler;
import net.scit.spring7.handler.LoginSuccessHandler;

@Configuration		//설정파일임을 나타태는 Annotation
@EnableWebSecurity	//스프링 시큐리티로 관리됨을 나타내는 Annotation
@RequiredArgsConstructor
public class SecurityConfig {
	
	//Handler 사용
	private final LoginSuccessHandler loginSuccessHandler;
	private final LoginFailureHandler loginFailureHandler;
	
	//요청 URL을 제어하는 메소드
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/",
								"/board/boardList",
								"/board/boardDetail",
								"/board/download",
								"/user/join",
								"/user/idCheck",
								"/user/joinProc",
								"/user/login",
								"/reply/replyAll",
								"/images/**",
								"/js/**",
								"/css/**").permitAll()					//인증절차없이 접근
				.requestMatchers("/admin").hasRole("ADMIN")				//admin으로 인증될때만 접근가능
				.requestMatchers("/user/mypage/**").hasAnyRole("ADMIN","USER")	//ROLE_ADMIN /my로 시작하는 요청은 ADMIN과 USER 접속할 수 있음
				.anyRequest().authenticated());							//기타 다른 경로는 인증된 사용자만 접근 가능
		
		//Custom 로그인
		http.formLogin((auth) -> auth
				.loginPage("/user/login")				//로그인 화면 요청 / 로그인 없이 특정페이지에 접속을 시도하면 무조건 로그인 페이지로 리다이렉팅
				.loginProcessingUrl("/user/loginProc")	//로그인 처리 요청 / 로그인 화면에서 버튼을 클릭하면 시큐리티가 받아서 처리해주는 경로 / 서비스단으로 가서 서비스 처리(userdetails)
				.usernameParameter("userId")		//사용자가 재정의한 아이디 / controller 역할을 함 / 파라미터 Security가 사용하는 파라미터 대신 
				.passwordParameter("userPwd")		//사용자가 재정의한 비번 파라미터
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler)
				.defaultSuccessUrl("/")				//로그인 성공시
//				.failureUrl("/user/login?error=true")	//로그인 실패시	//FailureHandler가 있으면 이 코드는 없어야함
				.permitAll());
		//세션은 아무나 들어올 수 없음
		
		//logout
		http
			.logout((auth) -> auth
					.logoutUrl("/user/logout")	//로그아웃 요청 URL
//					.logoutSuccessHandler((request, response, authentication) -> {
//						
//					})
					.logoutSuccessUrl("/")	//로그아웃 성공시 URL
					.invalidateHttpSession(true) //세션 무효화
					.clearAuthentication(true));
		
		//시큐리티는 사이트 위변조를 방어하도록 설정되어 있음
		//개발할 때에는 위변조 방어(CSRF)를 disable 시키고 배포시 enable 시킴
		//POST 요청 시 CSRF(Cross-Site request Forgery) 토큰을 요청함
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
