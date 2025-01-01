package net.scit.spring7.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.BoardDTO;
import net.scit.spring7.service.BoardService;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	
	/**
	 * 게시글 목록 요청
	 * @param model
	 * @return
	 */
	@GetMapping("/boardList")
	public String boardList(Model model) {
		
		List<BoardDTO> list = boardService.selectAll();
		model.addAttribute("list", list);
		
		return "board/boardList";
	}
	
	/**
	 * 게시글 화면 요청
	 * @return
	 */
	@GetMapping("/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	/**
	 * 게시글쓰기 처리 요청
	 * @param model
	 * @return
	 */
	@PostMapping("/boardWrite")
	public String boardWrite(@ModelAttribute BoardDTO boardDTO) {
		//DB 등록
		log.info("================ {}", boardDTO.toString());
		boardService.insertBoard(boardDTO);
		
		
		return "redirect:/board/boardList";
	}
	
	/**
	 * 조회수 증가
	 * @param boardSeq
	 * @param model
	 * @return
	 */
	@GetMapping("/boardDetail")
	public String boardDetail(@RequestParam(name="boardSeq")Long boardSeq, Model model) {//Model model은 데이터를 가져온다
		//DB에서 boardSeq에 해당하는 하나의 게시글을 조회
		
		BoardDTO boardDTO = boardService.selectOne(boardSeq);
		boardService.incrementHitcount(boardSeq);
		model.addAttribute("board", boardDTO);
		return "board/boardDetail";
	}
	
	/**
	 * 삭제를 위한 요청
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/boardDelete")
	public String boardDelete(@RequestParam(name="boardSeq")Long boardSeq) {
		boardService.deleteOne(boardSeq);
		return "redirect:/board/boardList";
	}
	
	/**
	 * 수정을 위한 화면 요청
	 * @param boardSeq
	 * @param model
	 * @return
	 */
	@GetMapping("/boardUpdate")
	public String boardUpdate(@RequestParam(name="boardSeq")Long boardSeq, Model model) {
		BoardDTO boardDTO = boardService.selectOne(boardSeq);
		model.addAttribute("board", boardDTO);
		return "board/boardUpdate";
	}
	
	/**
	 * 게시글 수정 처리 요청
	 * @param boardDTO
	 * @return
	 */
	@PostMapping("/boardUpdate")
	public String boardUpdate(@ModelAttribute BoardDTO boardDTO) {//데이터가 4개 날라와서 한꺼번에 받음
		log.info("==== 수정데이터: {}", boardDTO.toString());	
		boardService.updateBoard(boardDTO);
		return "redirect:/board/boardList";
	}
}
