package net.scit.review.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.review.entity.ReviewEntity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ReviewDTO {
	private Long reviewNum;
	private String reviewerNickname;
	private Long movieNum;
	private String reviewText;
	private Long score;
	private LocalDateTime reviewDate;
		
	public static ReviewDTO toDTO(ReviewEntity reviewEntity, Long movieNum) {
		return ReviewDTO.builder()
				.reviewNum(reviewEntity.getReviewNum())
				.reviewerNickname(reviewEntity.getReviewerNickname())
				.reviewText(reviewEntity.getReviewText())
				.score(reviewEntity.getScore())
				.reviewDate(reviewEntity.getReviewDate())
				.build();
	}
}
