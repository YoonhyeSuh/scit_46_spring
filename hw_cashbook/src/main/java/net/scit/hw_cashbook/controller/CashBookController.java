package net.scit.hw_cashbook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.hw_cash.dto.CashBookDTO;
import net.scit.hw_cashbook.service.CashBookService;

@Controller
@RequestMapping("/cashbook")
@Slf4j
@RequiredArgsConstructor
public class CashBookController {

	public final CashBookService cashBookService;
	
	@GetMapping("/record")
	public String record(Model model) {
		List<CashBookDTO> list = cashBookService.selectAll();
		model.addAttribute("list", list);
		return "cashbook/record";
	}
	
	@PostMapping("/record")
	public String record(@ModelAttribute CashBookDTO cashBookDTO, RedirectAttributes rttr) {
		cashBookService.insert(cashBookDTO);
		return "cashbook/record";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name="id")Long cashSeq, Model model) {
		cashBookService.delete(cashSeq);
		return "redirect:/cashbook/record";
	}
}
