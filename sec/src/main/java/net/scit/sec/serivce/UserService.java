package net.scit.sec.serivce;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.sec.dto.UserDTO;
import net.scit.sec.entity.UserEntity;
import net.scit.sec.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder BCryptPasswordencoder;

	/**
	 * 전달받은 userDTO를 Entity로 변환한 후 DB에 저장
	 * @param userDTO
	 */
	public void joinProc(UserDTO userDTO) {
		//암호화되기 전 데이터를 꺼내다
		UserEntity entity = UserEntity.toEntity(userDTO);
//		String pwd = entity.getUserPwd();	//암호화되지 않은 데이터 get으로 꺼냄
//		String encoded = BCryptPasswordencoder.encode(pwd);	//암호화
//		entity.setUserPwd(encoded);		//비번 암호화된걸로 다시 원래 상태인 entity에 집어 넣음
		entity.setUserPwd(BCryptPasswordencoder.encode(entity.getUserPwd()));
		
		log.info("---- {}", entity.toString());
		
		//db에 다시 저장
		userRepository.save(entity);
;	}

}
