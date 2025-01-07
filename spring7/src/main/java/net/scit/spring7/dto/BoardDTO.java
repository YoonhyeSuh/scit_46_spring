package net.scit.spring7.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring7.entity.BoardEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
//클라이언트가 입력한 것을 서버쪽으로 보내기
//서버쪽 디비 조회한 것을 클라이언트로 보내기
public class BoardDTO {
	private Long boardSeq;
	private String boardWriter;
	private String boardTitle;
	private String boardContent;
	private int hitCount;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	//첨부된 파일을 저장하는 멤버
	private MultipartFile uploadFile;

	//업로드 파일이 잇을 경우 View에서 사용하기 위해
	private String originalFileName;
	private String savedFileName;
	
	//entity -> dto
	//서버에서 클라이언트 화면에 보여준다
	public static BoardDTO toDTO(BoardEntity boardEntity) {
		return BoardDTO.builder()
				.boardSeq(boardEntity.getBoardSeq())
				.boardWriter(boardEntity.getBoardWriter())
				.boardTitle(boardEntity.getBoardTitle())
				.boardContent(boardEntity.getBoardContent())
				.hitCount(boardEntity.getHitCount())
				.createDate(boardEntity.getCreateDate())
				.updateDate(boardEntity.getUpdateDate())
				.originalFileName(boardEntity.getOriginalFileName())
				.savedFileName(boardEntity.getSavedFileName())
				.build();
	}
}
