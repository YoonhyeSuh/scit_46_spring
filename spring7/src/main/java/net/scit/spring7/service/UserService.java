package net.scit.spring7.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;
	
	/**
	 * 전달받은 userId가 DB에 존재하는지 여부 확인
	 * @param userId
	 * @return
	 */
	public boolean existId(String userId) {
		boolean result = userRepository.existsById(userId);
		log.info("아이디 존재여부: {}", result);		//가입하려면 false가 나와야함(클라이언트쪽에서는 true)
		return result;
	}

}
