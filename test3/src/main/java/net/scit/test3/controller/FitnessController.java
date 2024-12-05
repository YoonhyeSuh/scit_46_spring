package net.scit.test3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.test3.dto.FitnessDTO;
import net.scit.test3.service.FitnessService;

@Controller
@RequestMapping("/fitness")
@Slf4j
@RequiredArgsConstructor	//생성자 자동 생성
public class FitnessController {
	
	public final FitnessService fitnessService;	// = new FintessService();
	
	@GetMapping("/regist")
	public String regist() {
		return "fitness/regist";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute FitnessDTO fitnessDTO) {
		
		log.info("================= {}", fitnessDTO.toString());
		fitnessService.insert(fitnessDTO);
		
		return "redirect:/";
	}
	
	@GetMapping("/selectAll")
	public String selectAll(Model model) {
		List<FitnessDTO> list = fitnessService.selectAll();
		model.addAttribute("list", list);
		return "fitness/selectAll";
	}
}
