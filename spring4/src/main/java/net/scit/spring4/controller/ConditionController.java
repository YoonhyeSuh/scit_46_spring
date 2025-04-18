package net.scit.spring4.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/display")
public class ConditionController {
	@GetMapping("/condition")
	public String condition(Model model) {
		List<String> fruit = Arrays.asList("사과", "배", "딸기", "바나나", "복숭아");
		//static 객체 생성 안해도 바로 사용할 수 있음
		
		List<Integer> number = new ArrayList<>();
		for(int i = 1; i<=20; i++) {
			number.add(i*3);
		}
		model.addAttribute("fruit", fruit);
		model.addAttribute("number", number);
		return "display/condition";
	}
}
