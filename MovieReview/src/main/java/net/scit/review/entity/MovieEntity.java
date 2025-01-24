package net.scit.review.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.review.dto.MovieDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="movie")
public class MovieEntity {
	
	@Id
	@Column(name="movie_num")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long movieNum;
	
	@Column(name="genre")
	private String genre;
	
	@Column(name="movie_name")
	private String movieName;
	
	@Column(name="movie_summary")
	private String movieSummary;
	
	@Column(name="movie_date")
	@CreationTimestamp
	private LocalDateTime movieDate;
	
	@OneToMany(mappedBy = "movieEntity", cascade = CascadeType.REMOVE)
	private List<ReviewEntity> reviewEntity = new ArrayList<>();
	
	public static MovieEntity toEntity(MovieDTO movieDTO) {
		return MovieEntity.builder()
				.movieNum(movieDTO.getMovieNum())
				.genre(movieDTO.getGenre())
				.movieName(movieDTO.getMovieName())
				.movieSummary(movieDTO.getMovieSummary())
				.movieDate(movieDTO.getMovieDate())
				.build();
	}
}
