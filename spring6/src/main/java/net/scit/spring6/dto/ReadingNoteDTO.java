package net.scit.spring6.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReadingNoteDTO {
	private Integer readingSeq;
	private String readStatus;
	private LocalDate readDate;
	private String bookReview;
	private Integer bookSeq;
}
