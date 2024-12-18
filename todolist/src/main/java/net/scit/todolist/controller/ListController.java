package net.scit.todolist.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String list(Model model) {
		List<ListDTO> list = listService.selectAll();
	    model.addAttribute("list", list);

		return "todo/list";
	}
	
	@PostMapping("/list")
	public String list(@ModelAttribute ListDTO listDTO, Model model) {
		if(listDTO.getTodo() == null || listDTO.getTodo() == "") {
			System.out.println("오류");
		} else {
			listService.insert(listDTO);
			List<ListDTO> list = listService.selectAll();
		    model.addAttribute("list", list);
		}
		return "redirect:/todo/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name="seqno")int seqno) {
		listService.delete(seqno);		
		return "redirect:/todo/list";
	}
	
	@PostMapping("/selectChoice")
	public String selectChoice(@RequestParam(name="importance", defaultValue="높음")String importance, @RequestParam(name="categories", defaultValue="회사")String categories, Model model){
		List<ListDTO> list = listService.selectChoice(importance, categories);
		model.addAttribute("list", list);
		return "todo/list";
	}
}
