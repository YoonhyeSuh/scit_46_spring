package net.scit.carsharing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	@GetMapping("/orderList")
	public String orderList() {
	    // 예약 정보 페이지로 이동
	    return "order/orderList";
	}
}
