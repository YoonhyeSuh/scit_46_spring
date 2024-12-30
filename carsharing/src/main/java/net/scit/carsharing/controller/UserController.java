package net.scit.carsharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.UserDTO;
import net.scit.carsharing.service.UserService;

@Controller
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	/**
	 * 회원가입을 위한 화면 요쳥
	 * @return
	 */
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	/**
	 * 회원가입을 위한 처리 요청
	 * @param userDTO
	 * @return
	 */
	@PostMapping("/joinProc")
	public String join(@ModelAttribute UserDTO userDTO) {
		
		if(userService.joinProc(userDTO)) {
			log.info("=== {}", userDTO.toString());
			return "redirect:/login";
		} else {
			return "redirect:/join";
		}
	}
	
	/**
	 * 아이디 중복확인 체크
	 * @param userId
	 * @return
	 */
	@PostMapping("/idCheck")
	@ResponseBody	//ajax로 접근하기 때문
	public Boolean idCheck(@RequestParam(name="userId")String userId) {
		boolean result = userService.idCheck(userId);
		return result;
	}
	
	@GetMapping("/login")
	public String login(@RequestParam(value="error", required = false)String error, Model model) {//error를 받을 수 있는 변수 생성
		if(error != null) {
			model.addAttribute("error", error);
			model.addAttribute("errMessage", "아이디나 비밀번호가 틀렸습니다.");
		}

//		System.out.println("error ==> " + error);
		return "user/login";
	}
}
