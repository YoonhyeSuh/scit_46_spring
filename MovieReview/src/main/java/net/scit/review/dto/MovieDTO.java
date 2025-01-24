package net.scit.review.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.review.entity.MovieEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class MovieDTO {
	private Long movieNum;
	private String genre;
	private String movieName;
	private String movieSummary;
	private LocalDateTime movieDate;
	
	public static MovieDTO toDTO(MovieEntity movieEntity) {
		return MovieDTO.builder()
				.movieNum(movieEntity.getMovieNum())
				.genre(movieEntity.getGenre())
				.movieName(movieEntity.getMovieName())
				.movieSummary(movieEntity.getMovieSummary())
				.movieDate(movieEntity.getMovieDate())
				.build();
	}
}
