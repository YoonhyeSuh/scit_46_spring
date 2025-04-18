package net.scit.spring5.controller;

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
import net.scit.spring5.dto.PhoneDTO;
import net.scit.spring5.service.PhoneService;

@Controller
@RequestMapping("/phone")
@Slf4j
@RequiredArgsConstructor

public class PhoneController {
	
	public final PhoneService phoneService;
	
	@GetMapping("/regist")
	public String regist() {
		return "phone/registView";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute PhoneDTO phoneDTO) {
		log.info("================= {}", phoneDTO.toString());
		
		phoneService.insert(phoneDTO);	//controller에서 service 도착
		
		return "redirect:/";	//나중에 수정
	}
	
	@GetMapping("/selectOne")
	public String selectOne(@RequestParam(name="id")Integer id, Model model) {
		
		PhoneDTO phoneDTO = phoneService.selectOne(id);
		
		model.addAttribute("phone", phoneDTO);
		
		return "phone/read";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(name="id")Integer id) {
		phoneService.delete(id);
		return "redirect:/";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam(name="id")Integer id, Model model) {
		PhoneDTO phoneDTO = phoneService.selectOne(id);
		model.addAttribute("phone", phoneDTO);
		return "phone/update";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute PhoneDTO phoneDTO, RedirectAttributes rttr) {
		phoneService.update(phoneDTO);
		
		rttr.addAttribute("id", phoneDTO.getId());
		return "redirect:/phone/selectOne";
	}
	
	@GetMapping("/selectAll")
	public String selectAll(Model model) {
		//전체 조회 요청을 서비스에
		//리스트로 받아옴
		List<PhoneDTO> list = phoneService.selectAll();
		model.addAttribute("list", list);
		return "phone/list";
	}
}
