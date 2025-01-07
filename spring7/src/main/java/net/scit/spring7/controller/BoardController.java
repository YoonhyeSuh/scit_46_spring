package net.scit.spring7.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String boardList(@RequestParam(name="searchItem", defaultValue = "boardTitle")String searchItem, @RequestParam(name="searchWord", defaultValue = "")String searchWord, Model model) {
		
		log.info("== searchWord: {}", searchWord);
		log.info("== searchItem: {}", searchItem);
		
		List<BoardDTO> list = boardService.selectAll(searchItem, searchWord);
		model.addAttribute("list", list);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		
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
		
//		MultipartFile file = boardDTO.getUploadFile();
//		log.info("파일 : {}", file);
//		log.info("파일 : {}", file.getContentType());
//		log.info("파일 : {}", file.getOriginalFilename());
//		log.info("파일 : {}", file.getSize());
//		log.info("파일 : {}", file.isEmpty());
		
		
		return "redirect:/board/boardList";
	}
	
	/**
	 * 조회수 증가
	 * @param boardSeq
	 * @param model
	 * @return
	 */
	@GetMapping("/boardDetail")
	public String boardDetail(@RequestParam(name="boardSeq")Long boardSeq, @RequestParam(name="searchItem", defaultValue = "boardTitle")String searchItem, @RequestParam(name="searchWord", defaultValue = "")String searchWord, Model model) {//Model model은 데이터를 가져온다
		//DB에서 boardSeq에 해당하는 하나의 게시글을 조회
		
		BoardDTO boardDTO = boardService.selectOne(boardSeq);
		boardService.incrementHitcount(boardSeq);
		model.addAttribute("board", boardDTO);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		return "board/boardDetail";
	}
	
	/**
	 * 삭제를 위한 요청
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/boardDelete")
	public String boardDelete(@RequestParam(name="boardSeq")Long boardSeq, @RequestParam(name="searchItem", defaultValue = "boardTitle")String searchItem, @RequestParam(name="searchWord", defaultValue = "")String searchWord, RedirectAttributes redirectAttributes) {
		boardService.deleteOne(boardSeq);
		
		//redirect시 model을 사용할 수 없다
		redirectAttributes.addAttribute("searchItem", searchItem);
		redirectAttributes.addAttribute("searchWord", searchWord);
		return "redirect:/board/boardList";
	}
	
	/**
	 * 수정을 위한 화면 요청
	 * @param boardSeq
	 * @param model
	 * @return
	 */
	@GetMapping("/boardUpdate")
	public String boardUpdate(@RequestParam(name="boardSeq")Long boardSeq, @RequestParam(name="searchItem", defaultValue = "boardTitle")String searchItem, @RequestParam(name="searchWord", defaultValue = "")String searchWord, Model model) {
		BoardDTO boardDTO = boardService.selectOne(boardSeq);
		model.addAttribute("board", boardDTO);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		return "board/boardUpdate";
	}
	
	/**
	 * 게시글 수정 처리 요청
	 * @param boardDTO
	 * @return
	 */
	@PostMapping("/boardUpdate")
	public String boardUpdate(@ModelAttribute BoardDTO boardDTO, @RequestParam(name="searchItem", defaultValue = "boardTitle")String searchItem, @RequestParam(name="searchWord", defaultValue = "")String searchWord, RedirectAttributes redirectAttributes) {//데이터가 4개 날라와서 한꺼번에 받음
		log.info("==== 수정데이터: {}", boardDTO.toString());	
		boardService.updateBoard(boardDTO);
		redirectAttributes.addAttribute("searchItem", searchItem);
		redirectAttributes.addAttribute("searchWord", searchWord);
		return "redirect:/board/boardList";
	}
}
