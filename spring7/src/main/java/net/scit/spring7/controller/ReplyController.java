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
	
	/**
	 * boardSeq에 해당하는 전체 댓글 조회
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/replyAll")
	public List<ReplyDTO> replyAll(@RequestParam(name="boardSeq")Long boardSeq){
		List<ReplyDTO> list = replyService.replyAll(boardSeq);
		
		return list;
	}
	
	/**
	 * replySeq 댓글 데이터 삭제
	 * @param replySeq
	 * @return 
	 */
	@GetMapping("/replyDelete")
	public String replyDelete(@RequestParam(name="replySeq")Long replySeq) {
		replyService.replyDelete(replySeq);
		return "success";
	}
	
	/**
	 * 수정을 위한 조회
	 * @return
	 */
	@GetMapping("/replyUpdate")
	public ReplyDTO replyUpdate(@RequestParam(name="replySeq")Long replySeq) {
		ReplyDTO replyDTO = replyService.replySelectOne(replySeq);
		return replyDTO;
	}
	
	// 수정 처리
	@PostMapping("/replyUpdateProc")
	public String replyUpdateProc(
			@RequestParam(name = "replySeq") Long replySeq,
			@RequestParam(name = "replyContent") String replyContent
			) {
		replyService.updateProc(replySeq, replyContent);
		
		return "success";
	}
}
