package net.scit.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.resume.dto.UserDTO;
import net.scit.resume.service.UserService;

@Controller
@RequestMapping("/resume")
@Slf4j	//log찍어서 데이터 넘어오는지 확인
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/joinProc")
	public String joinProc(@ModelAttribute UserDTO userDTO) {
		//UserDTO toDTO() 생성
		//UserEntity toEntity() 보완
		
		log.info("회원 정보: {}", userDTO.toString());
		boolean result = userService.joinProc(userDTO);
		
		return "redirect:/";
	}
}
