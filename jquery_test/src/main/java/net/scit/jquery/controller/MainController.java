package net.scit.jquery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.scit.jquery.dto.MemberDTO;

@Controller
public class MainController {
	@GetMapping({"/", ""})
	public String index() {
		return "index";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute MemberDTO member, Model model) {
		System.out.println(member);
		
		model.addAttribute("member", member);
		
		return "index";
	}
}

