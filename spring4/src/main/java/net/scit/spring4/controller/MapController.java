package net.scit.spring4.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.scit.spring4.dto.FriendDTO;

@Controller
@RequestMapping("/display")
public class MapController {
	@GetMapping("/mapData")
	public String mapData(Model model) {
		
		Map<String, FriendDTO> map = new HashMap<>();
		
		map.put("son", new FriendDTO("손오공", 28, "111", LocalDate.now(), true));
		map.put("sa", new FriendDTO("사오정", 31, "222", LocalDate.now(), false));
		map.put("lee", new FriendDTO("이순신", 45, "333", LocalDate.now(), true));
		map.put("lim", new FriendDTO("임꺽정", 24, "444", LocalDate.now(), true));
		
		int age = 13;
		
		model.addAttribute("age", age);
		model.addAttribute("friend", map);
		return "display/mapData";
	}
	
	@GetMapping("/sendData")
	public String sendData(@RequestParam(name="name")String name, @RequestParam(name="age")int age) {
		System.out.println(name + ":" + age);
		return "redirect:/";	//리다이렉트 : /를 재요청
	}
}
