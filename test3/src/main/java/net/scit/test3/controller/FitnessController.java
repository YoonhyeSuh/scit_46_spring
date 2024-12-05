package net.scit.test3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.scit.test3.dto.FitnessDTO;
import net.scit.test3.service.FitnessService;

@Controller
@RequestMapping("/fitness")
public class FitnessController {
	//public final FitnessService fitnessService;
	
	@GetMapping("/regist")
	public String regist() {
		return "fitness/regist";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute FitnessDTO fitnessDTO) {
		//fitnessService.insert(fitnessDTO);
		System.out.println(fitnessDTO);
		return "fitness/regist";
	}
}
