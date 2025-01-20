package net.scit.spring7.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.UserDTO;
import net.scit.spring7.entity.UserEntity;
import net.scit.spring7.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	/**
	 * 전달받은 userId가 DB에 존재하는지 여부 확인
	 * @param userId
	 * @return
	 */
	public boolean existId(String userId) {
		boolean result = userRepository.existsById(userId);
		//log.info("아이디 존재여부: {}", result);		//가입하려면 false가 나와야함(클라이언트쪽에서는 true)
		return !result;
	}

	/**
	 * 회원 가입 처리
	 * @param userDTO
	 * @return
	 */
	public boolean joinProc(UserDTO userDTO) {
		//비밀버호 암호화
		userDTO.setUserPwd(bCryptPasswordEncoder.encode(userDTO.getUserPwd()));
		
		UserEntity entity = UserEntity.toEntity(userDTO);
		userRepository.save(entity);
		
		boolean result = userRepository.existsById(userDTO.getUserId());
		return result;
	}

	/**
	 * 입력한 비밀번호가 맞는지 확인
	 * @param userId
	 * @param userPwd
	 * @return
	 */
	public UserDTO pwdCheck(String userId, String userPwd) {
		Optional<UserEntity> temp = userRepository.findById(userId);
	
		if(temp.isPresent()) {
			UserEntity entity = temp.get();
			String encodedPwd = entity.getUserPwd();	//암호화된 비번
			
			boolean result = bCryptPasswordEncoder.matches(userPwd, encodedPwd);	//입력된 비번, DB 암호화
			if(result) {
				return UserDTO.toDTO(entity);
			}
		}
		return null;
	}

	/**
	 * DB에서 개인정보 수정 처리
	 * @param userDTO
	 */
	@Transactional
	public void updateProc(UserDTO userDTO) {
		String id = userDTO.getUserId();
		Optional<UserEntity> temp = userRepository.findById(id);
		
		if(temp.isPresent()) {
			UserEntity entity = temp.get();
			
			//사용자가 입력한 정보 (비밀번호와 이메일)ㄴ
			String encodedPwd = bCryptPasswordEncoder.encode(userDTO.getUserPwd());
			String email = userDTO.getEmail();
			
			//DB에서 뽑아낸 정보 (비밀번호와 이메일)
			entity.setUserPwd(encodedPwd);
			entity.setEmail(email);
		}
	}

}
