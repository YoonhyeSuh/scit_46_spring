package net.scit.ajax.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.scit.ajax.CustomerDTO;

@Controller
//@RestController	페이지 반환
public class CustomerController {
	
	@GetMapping("/customer")
	public String customer() {
		return "customer";
	}
	
	@GetMapping("/list")
	@ResponseBody	//데이터가 반환
	public List<CustomerDTO> customerList(){
		//DB에서 조회된 데이터라고 가정
		List<CustomerDTO> list = Arrays.asList(
				new CustomerDTO(1, "김동영", "010-1996-0201", "남성", "서울시 종로구"),
				new CustomerDTO(1, "이제노", "010-2000-0423", "남성", "서울시 성동구"),
				new CustomerDTO(1, "리쿠", "010-2003-0628", "남성", "서울시 강남구"),
				new CustomerDTO(1, "유우시", "010-2004-0405", "남성", "서울시 성북구"),
				new CustomerDTO(1, "샤오쥔", "010-1999-0808", "남성", "서울시 용산구")
			);
		return list;
	}
}
