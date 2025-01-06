package net.scit.spring7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.service.UserService;

@Controller
@RequestMapping("/user")
@Slf4j	//log찍어서 데이터 넘어오는지 확인
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
//	
//	@PostMapping("/joinProc")
//	public String join(@ModelAttribute UserDTO userDTO) {
//		return "redirect:/";
//	}
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(name="userId")String userId) {
		boolean result = userService.existId(userId);
		return true;
	}
}
