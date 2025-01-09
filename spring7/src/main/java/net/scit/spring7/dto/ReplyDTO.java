package net.scit.spring7.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring7.entity.ReplyEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReplyDTO {
	private Long replySeq;
	private Long boardSeq;
	private String replyWriter;
	private String replyContent;
	private LocalDateTime createDate;
	
	//Entity --> DTO
	public static ReplyDTO toDTO(ReplyEntity replyEntity, Long boardSeq) {
		return ReplyDTO.builder()
				.replySeq(replyEntity.getReplySeq())
				.replyWriter(replyEntity.getReplyWriter())
				.replyContent(replyEntity.getReplyContent())
				.createDate(replyEntity.getCreateDate())
				.build();
	}
}
