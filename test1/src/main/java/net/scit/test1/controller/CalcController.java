package net.scit.test1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalcController {
	
	@GetMapping("/calc")
	public String calc(@RequestParam(name="x")int x, @RequestParam(name="y")int y) {
		int result = x + y;
		System.out.println(result);
		return "calcResult";
	}

}