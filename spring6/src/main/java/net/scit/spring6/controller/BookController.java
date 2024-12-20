package net.scit.spring6.controller;

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
import net.scit.spring6.dto.BookDTO;
import net.scit.spring6.service.BookService;

@Controller
@RequestMapping("/book")
@Slf4j
@RequiredArgsConstructor
public class BookController {
	
	public final BookService bookService;
	
	@GetMapping("/regist")
	public String regist(Model model) {
		List<BookDTO> list = bookService.selectAll();
		model.addAttribute("list", list);
		return "book/regist";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute BookDTO bookDTO, Model model) {
		bookService.insert(bookDTO);
		List<BookDTO> list = bookService.selectAll();
		model.addAttribute("list", list);
		return "redirect:/book/regist";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name="bookSeqno")Integer bookSeqno) {
		bookService.delete(bookSeqno);
		return "redirect:/book/regist";
	}
}
