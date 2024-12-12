package net.scit.todolist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.todolist.dto.ListDTO;
import net.scit.todolist.serivce.ListService;


@Controller
@RequestMapping("/todo")
@Slf4j
@RequiredArgsConstructor
public class ListController {
	
	public final ListService listService;
	@GetMapping("/list")
	public String list() {
		
		return "todo/list";
	}
	
	@PostMapping("/list")
	public String list(@ModelAttribute ListDTO listDTO) {
		listService.insert(listDTO);
		return "redirect:/";
	}
}
