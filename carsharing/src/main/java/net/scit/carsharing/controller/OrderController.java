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
import net.scit.carsharing.dto.OrderDTO;
import net.scit.carsharing.service.OrderService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;
	
	@GetMapping("/orderList")
	public String orderList() {
	    // 예약 정보 페이지로 이동
	    return "order/orderList";
	}
	
	@ResponseBody
	@GetMapping("/reserveList")
	public List<OrderDTO> orderSelectAll(){
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<OrderDTO> list = orderService.orderSelectAll(userId);
		return list;
	}
	
	@ResponseBody
	@PostMapping("/return")
	public Map<String, Object> returnCar(@RequestParam(name="carSeq") Integer carSeq,@RequestParam(name="orderSeq") Integer orderSeq) {
		
		// 현재 로그인된 사용자 ID 가져오기
	    String userId = SecurityContextHolder.getContext().getAuthentication().getName();
	    
	    boolean success = orderService.returnCar(orderSeq, carSeq, userId);
	    Map<String, Object> result = new HashMap<>();
	    result.put("success", success);
	    return result;
	}
}
