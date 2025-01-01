package net.scit.carsharing.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.LoginUserDetails;
import net.scit.carsharing.entity.UserEntity;
import net.scit.carsharing.repository.UserRepository;

@Service	//userdetailsservice를 상속받기 때문에 굳이 안써도 된다
@RequiredArgsConstructor
@Slf4j
public class LoginUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;	//@RequiredArgsConstructor 짝꿍 무조건 써야함
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// Repository로 연결하는 코드
		UserEntity userEntity = userRepository.findByUserId(userId);

		if (userEntity != null) {	// 올바른 값을 입력
			// 일반 DTO로 변환하면 안되오!
			// LoginUserDetails로 변환해서 반환해야 함!
			// userDetails는 DTO의 한 종류
			LoginUserDetails userDetails = LoginUserDetails.toDTO(userEntity);			
			
			return userDetails;
		} else	{
			throw new UsernameNotFoundException("오류났지!!");		// 이 메세지를 사용하려면 새로운 클래스를 만들어야 한다
		}
	}


}
