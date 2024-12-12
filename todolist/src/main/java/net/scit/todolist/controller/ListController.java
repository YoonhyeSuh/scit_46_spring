package net.scit.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.scit.todolist.dto.ListDTO;


@Controller
@RequestMapping("/todo")
public class ListController {
	@GetMapping("/list")
	public String list() {
		
		return "todo/list";
	}
	
	@PostMapping("/list")
	public String list(@ModelAttribute ListDTO listDTO, Model model) {
		model.addAttribute("list", listDTO);
		return "todo/list";
	}
}
