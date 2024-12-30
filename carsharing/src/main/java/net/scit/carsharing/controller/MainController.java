package net.scit.carsharing.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.scit.carsharing.dto.LoginUserDetails;

@Controller
public class MainController {
	@GetMapping({"/",""})
	public String index(@AuthenticationPrincipal LoginUserDetails loginUserDetail, Model model) {
		
		if(loginUserDetail != null) {			
			String loginId = loginUserDetail.getUsername();			//아이디
			model.addAttribute("loginId", loginId);
		}
		return "index";
	}
}
