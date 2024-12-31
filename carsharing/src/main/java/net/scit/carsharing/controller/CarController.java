package net.scit.carsharing.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.CarDTO;
import net.scit.carsharing.dto.LoginUserDetails;
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
	public String reservated(@AuthenticationPrincipal LoginUserDetails loginUserDetail,@RequestParam(name="carSeq") Integer carSeq) {
		if(carService.reservated(loginUserDetail.getUserId(), carSeq)) {
			return "order/orderList";
		} else {
			return "car/carList";
		}
	}
}
