package net.scit.resume.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping({"/", ""})
	   private String index() {
	      return "index";
	   }
}
