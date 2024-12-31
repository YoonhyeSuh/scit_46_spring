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
		
		UserEntity entity = userRepository.findByUserId(userId);
		if(entity != null) {
			LoginUserDetails userDetails = LoginUserDetails.toDTO(entity);
			return userDetails;
		} else {
			throw new UsernameNotFoundException("로그인 정보가 없습니다.");
		}
	}

}
