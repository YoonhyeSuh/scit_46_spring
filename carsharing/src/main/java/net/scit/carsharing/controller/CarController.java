package net.scit.carsharing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.CarDTO;
import net.scit.carsharing.service.CarService;

@Controller
@RequestMapping("/car")
@Slf4j
@RequiredArgsConstructor
public class CarController {
	
	public final CarService carService;
	
	@GetMapping("/carList")
	public String carList() {
		return "car/carList";
	}
	
	@GetMapping("/list")
	@ResponseBody
	public List<CarDTO> list(){
		List<CarDTO> list = carService.selectAll();
		return list;
	}
	
	@PostMapping("/reservated")
	@ResponseBody
	public Map<String, Object> reservated(
			@RequestParam(name="carSeq") Integer carSeq) {
		
		// 현재 로그인된 사용자 ID 가져오기
	    String userId = SecurityContextHolder.getContext().getAuthentication().getName();
	    
	    boolean success = carService.reservated(carSeq, userId);
	    Map<String, Object> result = new HashMap<>();
	    result.put("success", success);
	    return result;
	}
}
