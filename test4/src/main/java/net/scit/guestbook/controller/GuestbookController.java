package net.scit.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@GetMapping("/guestbookRegist")
	public String regist() {
		return "regist";
	}
}
