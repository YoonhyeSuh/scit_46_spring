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
	
	@GetMapping("/movieRegist")
	public String movieRegist() {
		return "movie_regist";
	}
	
	@PostMapping("/movieRegist")
	public String movieRegist(@ModelAttribute MovieDTO movieDTO) {
		movieService.insertMovie(movieDTO);
		return "redirect:/";
	}
	
	@PostMapping("/movieCheck")
	@ResponseBody
	public boolean idCheck(@RequestParam(name="movieName")String movieName) {
		boolean result = movieService.existName(movieName);
		return result;
	}
	
	@GetMapping("/movieDelete")
	public String movieDelete(@RequestParam(name="movieNum")Long movieNum) {
		movieService.deleteOne(movieNum);
		return "redirect:/";
	}
	
	@GetMapping("/recent")
    public String getMoviesByRecent(Model model) {
        List<MovieDTO> list = movieService.findAllSortedByDate();
        model.addAttribute("list", list);
        return "movie_board";
    }
	
	@GetMapping("/genreList")
    public String getMoviesByGenreList(Model model) {
        List<MovieDTO> list = movieService.findAllSortedByGenreList();
        model.addAttribute("list", list);
        return "movie_board";
    }
	
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
	
	@PostMapping("/reviewRegist")
	public String reviewRegist(@ModelAttribute ReviewDTO reviewDTO) {
		movieService.insertReview(reviewDTO);
		return "redirect:/";
	}

}
