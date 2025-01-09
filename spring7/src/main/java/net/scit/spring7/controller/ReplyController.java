package net.scit.spring7.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.LoginUserDetails;
import net.scit.spring7.dto.ReplyDTO;
import net.scit.spring7.service.ReplyService;

@RestController
@RequestMapping("/reply")
@Slf4j
@RequiredArgsConstructor	//final이기 때문에
public class ReplyController {
	
	private final ReplyService replyService;
	
	@PostMapping("/replyInsert")
	public String replyInsert(@ModelAttribute ReplyDTO replyDTO, @AuthenticationPrincipal LoginUserDetails loginUser) {
		String loginId = loginUser.getUserId();
		replyDTO.setReplyWriter(loginId);
		
		//log.info("댓글 정보: {}", replyDTO.toString());
	
		replyService.insert(replyDTO);
		return "success";
	}
	
	@GetMapping("/replyAll")
	public List<ReplyDTO> replyAll(@RequestParam(name="boardSeq")Long boardSeq){
		List<ReplyDTO> list = replyService.replyAll(boardSeq);
		
		return list;
	}
}
