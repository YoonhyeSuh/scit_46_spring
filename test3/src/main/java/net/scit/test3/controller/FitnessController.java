package net.scit.test3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.scit.test3.dto.FitnessDTO;

@Controller
@RequestMapping("/fitness")
public class FitnessController {
	
	@GetMapping("/regist")
	public String regist() {
		return "fitness/regist";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute FitnessDTO fitnessDTO) {
		
		return "redirect:/";
	}
}
