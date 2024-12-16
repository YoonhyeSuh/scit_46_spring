package net.scit.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {
	@GetMapping({"/ajaxReq1"})
	@ResponseBody
	public String ajaxReq1() {
		return "Received Success!!";
	}
}
