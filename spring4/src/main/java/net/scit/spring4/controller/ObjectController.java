package net.scit.spring4.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.scit.spring4.dto.FriendDTO;

@Controller
@RequestMapping("/display")
public class ObjectController {
	
	@GetMapping("/object")
	public String obj(Model model) {
		FriendDTO friend = new FriendDTO("전우치", 25, "010-1234-1234", LocalDate.of(2000,12,25), true);
		
		List<FriendDTO> fList = Arrays.asList(
				new FriendDTO("손오공", 23, "010-2323-1414", LocalDate.of(2002,1,1), true),
				new FriendDTO("사오정", 31, "010-1111-5555", LocalDate.of(1994,12,11), false),
				new FriendDTO("저팔계", 26, "010-2222-9999", LocalDate.of(1999,10,10), false),
				new FriendDTO("삼장법사", 45, "010-3333-8888", LocalDate.of(1979,11,11), true),
				new FriendDTO("이몽룡", 35, "010-4444-7777", LocalDate.of(1989,1,15), true)
				);
		model.addAttribute("friend", friend);
		model.addAttribute("fList", fList);
		
		return "display/obj";
	}

}
