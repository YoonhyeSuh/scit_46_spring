package net.scit.spring4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/display")
public class ThymeleafController {
	@GetMapping("/text")
	public String text(Model model) {
		String korean = "대한민국!!! ♥♥♥♥♥♥";
		String english = "I have a dream!";
		String japanese = "頑張る";
		String tag = "<marquee>돌이 굴러가유</marquee>";
		String url = "https://www.naver.com";
		
		//숫자
		int age = 25;
		double pi = Math.PI;
		
		//빈 데이터
		//nullDate 참조형 가리키는게 없음
		//emptyDate 가리키는데 가리키는 곳에 가면 아무것도 없음
		String nullData = null;
		String emptyData = "";
		
		//nullData.charAt(1);		//NullPointerException
		//emptyData.charAt(1);	//IndexOutOfBoundsException
		
		//. = 참조연산자
		model.addAttribute("kor", korean);
		model.addAttribute("eng", english);
		model.addAttribute("jap", japanese);
		model.addAttribute("tag", tag);
		model.addAttribute("url", url);
		model.addAttribute("age", age);
		model.addAttribute("pi", pi);
		
		return "display/text";
	}
}
