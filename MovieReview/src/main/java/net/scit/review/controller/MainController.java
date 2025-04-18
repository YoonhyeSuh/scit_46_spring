package net.scit.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import net.scit.review.dto.MovieDTO;
import net.scit.review.service.MovieService;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final MovieService movieService;
	
	//메인 화면
	//영화 정보 리스트를 보여준다
	@GetMapping({"/", ""})
	public String movieList(Model model) {
		List<MovieDTO> list = movieService.selectAll();
	    model.addAttribute("list", list);
		return "movie_board";
	}
}
