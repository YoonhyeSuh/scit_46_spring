package net.scit.jquery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping({"/", ""})
	public String index() {
		return "index";
	}
}

//아이디(String), 이메일(String), 사는동네(String), 나이(int): 20~50