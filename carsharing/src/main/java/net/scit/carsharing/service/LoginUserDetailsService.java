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
		
		//Repository로 연결하는 코드
		//UserDetails는 DTO의 한 종류
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		//일반 DTO로 변환하면 안됨
		//LoginUserDetails로 변환해서 반환해야함
		
		LoginUserDetails userDTO = LoginUserDetails.toDTO(userEntity);
		return userDTO;
	}

}
