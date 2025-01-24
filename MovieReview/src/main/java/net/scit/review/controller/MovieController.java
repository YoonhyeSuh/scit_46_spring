package net.scit.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.review.dto.MovieDTO;
import net.scit.review.dto.ReviewDTO;
import net.scit.review.service.MovieService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MovieController {
	
	private final MovieService movieService;
	
	//영화 정보 등록 페이지
	@GetMapping("/movieRegist")
	public String movieRegist() {
		return "movie_regist";
	}
	
	//영화 정보를 입력하여 등록
	//영화db에 저장
	@PostMapping("/movieRegist")
	public String movieRegist(@ModelAttribute MovieDTO movieDTO) {
		movieService.insertMovie(movieDTO);
		return "redirect:/";
	}
	
	//영화 제목 중복 체크
	@PostMapping("/movieCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(name="movieName")String movieName) {
		boolean result = movieService.existName(movieName);
		return result;
	}
	
	//영화 정보 삭제
	@GetMapping("/movieDelete")
	public String movieDelete(@RequestParam(name="movieNum")Long movieNum) {
		movieService.deleteOne(movieNum);
		return "redirect:/";
	}
	
	//영화 정보 최신 등록 순으로 조회
	@GetMapping("/recent")
    public String getMoviesByRecent(Model model) {
        List<MovieDTO> list = movieService.findAllSortedByDate();
        model.addAttribute("list", list);
        return "movie_board";
    }
	
	//영화 정보 장르 순으로 조회
	@GetMapping("/genreList")
    public String getMoviesByGenreList(Model model) {
        List<MovieDTO> list = movieService.findAllSortedByGenreList();
        model.addAttribute("list", list);
        return "movie_board";
    }
	
	//영화 정보 장르 골라보기 조회
	//장르에 따라 영화 정보 리스트가 보여진다
	@GetMapping("/genre")
	public String getMoviesByGenre(@RequestParam(name = "genre", required = false) String genre, Model model) {
	    List<MovieDTO> movies;

	    if (genre == null || genre.isEmpty()) {
	        movies = movieService.selectAll();
	    } else {
	        movies = movieService.getMoviesByGenre(genre);
	    }

	    model.addAttribute("list", movies);
	    model.addAttribute("searchItem", genre);

	    return "movie_board";
	}
	
	//영화 리뷰 페이지
	//각 영화 정보에 해당하는 리뷰 리스트를 보여준다
	//영화 평점 계산
	@GetMapping("/review")
    public String review(@RequestParam(name = "movieNum") Long movieNum, Model model) {
    	MovieDTO movieDTO = movieService.selectOne(movieNum);
    	List<ReviewDTO> review = movieService.reviewList(movieNum);
    	
    	double averageScore = review.isEmpty() 
                ? 0.0 
                : review.stream()
                            .mapToDouble(ReviewDTO::getScore)
                            .average()
                            .orElse(0.0);
    	
    	model.addAttribute("review", review);
    	model.addAttribute("list", movieDTO);
    	model.addAttribute("averageScore", averageScore);
    	
    	return "movie_review";
    }
	
	//영화 리뷰 등록
	@PostMapping("/reviewRegist")
	public String reviewRegist(@ModelAttribute ReviewDTO reviewDTO) {
		movieService.insertReview(reviewDTO);
		return "redirect:/";
	}

}
