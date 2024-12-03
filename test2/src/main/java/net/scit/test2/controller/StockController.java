package net.scit.test2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.scit.test2.dto.StockDTO;

@Controller
@RequestMapping("/stock")
@Slf4j
public class StockController {
	/*
		재고 데이터 입력을 위한 화면 요청
		@return
	*/
	@GetMapping("/stockInsert")
	public String stockInsert() {
		return "stock/stockInsert";
	}
	
	/*
		재고 데이터를 입력받아 처리
		@return
	*/
	@PostMapping("/stockResult")
	public String stockResult(@ModelAttribute StockDTO stockDTO, Model model){
		
		log.info("stockDTO : {}", stockDTO,toString());
		model.addAttribute("stock", stockDTO);
		//System.out.println(stockDTO);
		return "stock/stockResult";		//반드시 forwarding으로 반환해야 출력할 수 있다
	}
}
