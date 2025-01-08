package net.scit.spring7.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.BoardDTO;
import net.scit.spring7.service.BoardService;
import net.scit.spring7.util.FileService;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
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
	public String boardUpdate(@ModelAttribute BoardDTO boardDTO, @RequestParam(name="boardSeq")Long boardSeq, @RequestParam(name="searchItem", defaultValue = "boardTitle")String searchItem, @RequestParam(name="searchWord", defaultValue = "")String searchWord, Model model) {
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
	
	/**
	 * 쓰레기통 아이콘을 클릭하여 파일만 삭제하는 작업
	 * @param boardSeq
	 * @return
	 */
	
	@GetMapping("/deleteFile")
	public String deleteFile(@RequestParam(name="boardSeq")Long boardSeq, @RequestParam(name="searchItem", defaultValue = "boardTitle")String searchItem, @RequestParam(name="searchWord", defaultValue = "")String searchWord, RedirectAttributes rttr) {
		
		// 2) DB도 수정 -> file 컬럼 두개의 값을 null로
		BoardDTO boardDTO = boardService.selectOne(boardSeq);
		
		String savedFileName = boardDTO.getSavedFileName();
		String fullPath = uploadPath + "/" + savedFileName;
		
		// 1) 물리적으로 존재하는 파일을 삭제
		boolean result = FileService.deleteFile(fullPath);
		log.info("삭제결과: {}", result);
		
		// 2) DB도 수정 -> file 컬럼 두개의 값을 null로
		boardService.deleteFile(boardSeq);
		
		//메소드 호출시 필요한 리퀘스트 파라미트를 rttr에 넣어서 가져온다
		rttr.addAttribute("boardSeq", boardSeq);
		rttr.addAttribute("searchItem", searchItem);
		rttr.addAttribute("searchWord", searchWord);
		//서치아이템, 서치워드도 보내야함
		//클라이언트에서 보내야함
		return "redirect:/board/boardDetail";
	}
	
	@GetMapping("/download")
	public String download(@RequestParam(name="boardSeq")Long boardSeq, HttpServletResponse response) {
		BoardDTO boardDTO = boardService.selectOne(boardSeq);
		String savedFileName = boardDTO.getSavedFileName();
		String originalFileName = boardDTO.getOriginalFileName();
				
		try {
			String tempName = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8.toString());
			response.setHeader("Content-Disposition", "attachment;filename=" + tempName);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		String fullPath = uploadPath + "/" + savedFileName;

		FileInputStream fin = null;				//로컬에서 업로드
		ServletOutputStream fout = null;		//네트워크로 output
		
		try {
			fin = new FileInputStream(fullPath);
			fout = response.getOutputStream();
			
			FileCopyUtils.copy(fin, fout);	//스트림?(링)으로 데이터를 내려보낸다
			
			fout.close();	//리소스 정리
			fin.close();	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;	//다운로드 기능만 수행하면 되서 null로 리턴해도 상관없음
	}
}
