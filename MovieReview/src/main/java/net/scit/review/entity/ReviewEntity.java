package net.scit.review.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.review.dto.ReviewDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="review")
public class ReviewEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_num")
	private Long reviewNum;
	
	@Column(name = "reviewer_nickname")
	private String reviewerNickname;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="movie_num")
	private MovieEntity movieEntity;
	
	@Column(name="review_text")
	private String reviewText;
	
	@Column(name = "score")
	private Long score;
	
	@Column(name = "review_date")
	@CreationTimestamp
	private LocalDateTime reviewDate;
	
	public static ReviewEntity toEntity(ReviewDTO reviewDTO, MovieEntity movieEntity) {
		return ReviewEntity.builder()
				.reviewNum(reviewDTO.getReviewNum())
				.reviewerNickname(reviewDTO.getReviewerNickname())
				.movieEntity(movieEntity)
				.reviewText(reviewDTO.getReviewText())
				.score(reviewDTO.getScore())
				.reviewDate(reviewDTO.getReviewDate())
				.build();
	}
}
