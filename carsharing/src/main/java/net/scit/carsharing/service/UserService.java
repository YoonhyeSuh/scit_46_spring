package net.scit.carsharing.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.UserDTO;
import net.scit.carsharing.entity.UserEntity;
import net.scit.carsharing.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public boolean joinProc(UserDTO userDTO) {
		
		
		
		//암호화되기 전 데이터를 꺼내다
		UserEntity entity = UserEntity.toEntity(userDTO);
		entity.setUserPw(bCryptPasswordEncoder.encode(entity.getUserPw()));
		log.info("---- {}", entity.toString());
		
		//db에 다시 저장
		userRepository.save(entity);
		
		return true;
	}
	
	public boolean idCheck(String userId) {
		//exists 쿼리메소드 boolean 값을 반환
		boolean result = userRepository.existsByUserId(userId);
		
		//UserEntity entity = userRepository.findByUserId(userId);	//pk가 아니기 때문에 findById를 사용 못함
		//System.out.println("===============" + result);		//있는 아이디 true, 없는 아이디 false
		
		return !result;
	}
}
