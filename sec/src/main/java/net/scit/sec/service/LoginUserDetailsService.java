package net.scit.sec.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.sec.dto.LoginUserDetails;
import net.scit.sec.entity.UserEntity;
import net.scit.sec.repository.UserRepository;

@Service	//userdetailsservice를 상속받기 때문에 굳이 안써도 된다
@RequiredArgsConstructor
@Slf4j
public class LoginUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;	//@RequiredArgsConstructor 짝꿍 무조건 써야함

	//public과 변수명만 바꿀 수 있음
	//오버라이드는 상속받은 메소드를 다시 재정의하는 행위
	//매개변수명과 접근지정자보다 큰 지정자로 바꾸는 것만 가능
	//메소드 시그니처 : 매소드 표현 기본 방법
	//loadUserByUsername() 비밀번호 비교는 명시적으로 없다!
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		log.info("userId :{}", userId);
		//Repository로 연결하는 코드
		//UserDetails는 DTO의 한 종류
		UserEntity userEntity = userRepository.findByUserId(userId);

		//일반 DTO로 변환하면 안됨
		//LoginUserDetails로 변환해서 반환해야함
		LoginUserDetails userDTO = LoginUserDetails.toDTO(userEntity);

		return userDTO;	//다형성 때문에 가능(부모를 반환할 수 있게 함)


		//		log.info("{}", userEntity.getUserName());
		//		LoginUserDetails userDetails = LoginUserDetails.toDTO(userEntity);
		//		return userDetails;
	}

}
