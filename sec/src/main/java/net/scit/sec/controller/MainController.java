package net.scit.sec.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.scit.sec.dto.LoginUserDetails;

@Controller
public class MainController {
	@GetMapping({"/",""})
	public String index(@AuthenticationPrincipal LoginUserDetails loginUserDetail, Model model) {
		
		if(loginUserDetail != null) {
			String loginName = loginUserDetail.getUserName();		//실명
			model.addAttribute("loginName", loginName);
			
			String loginId = loginUserDetail.getUsername();			//아이디
			model.addAttribute("loginId", loginId);
		}
		return "index";
	}
}
