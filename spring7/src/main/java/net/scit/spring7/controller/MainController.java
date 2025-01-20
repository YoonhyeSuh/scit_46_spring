package net.scit.spring7.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.scit.spring7.dto.LoginUserDetails;

@Controller
public class MainController {
	@GetMapping({"/", ""})
	   private String index(@AuthenticationPrincipal LoginUserDetails loginUser, Model model) {
		
		//로그인을 한 경우
		if(loginUser != null) {
			 String loginName = loginUser.getUserName();
	         model.addAttribute("loginName", loginName);
	         
//	         loginUser는 객체의 정보를 다 전달함
	         model.addAttribute("loginUser", loginUser);
		}
	      return "index";
	   }
}
