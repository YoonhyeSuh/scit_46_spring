package net.scit.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecondController {
	@GetMapping("/param")
	public String param(@RequestParam(name="username")String username, @RequestParam(name="age")int age) {
		System.out.println("이름: " + username);
		System.out.println("나이: " + age);
		return "param";
	}
	
	@GetMapping("/info")
	public String info(@RequestParam(name="id")int id, @RequestParam(name="major")String major, @RequestParam(name="skill")String skill) {
		System.out.println("학번: " + id);
		System.out.println("전공: " + major);
		System.out.println("기술: " + skill);
		return "info";
	}
}
