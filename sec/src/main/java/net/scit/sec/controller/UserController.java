package net.scit.sec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.sec.dto.UserDTO;
import net.scit.sec.serivce.UserService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	/**
	 * 회원가입을 위한 화면 요쳥
	 * @return
	 */
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	/**
	 * 회원가입을 위한 처리 요청
	 * @param userDTO
	 * @return
	 */
	@PostMapping("/joinProc")
	public String join(@ModelAttribute UserDTO userDTO) {
		log.info("=== {}", userDTO.toString());
		userService.joinProc(userDTO);
		return "redirect:/";
	}
	
	/**
	 * 아이디 중복확인 체크
	 * @param userId
	 * @return
	 */
	@PostMapping("/idCheck")
	@ResponseBody	//ajax로 접근하기 때문
	public Boolean idCheck(@RequestParam(name="userId")String userId) {
		return true;
	}
}
