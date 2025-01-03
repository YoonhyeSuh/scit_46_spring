package net.scit.spring7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.scit.spring7.dto.UserDTO;

@Controller
@RequestMapping("/user")
public class UserController {

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
}
