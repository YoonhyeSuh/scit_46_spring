package net.scit.spring7.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
import net.scit.spring7.dto.BoardDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="board")
@EntityListeners(AuditingEntityListener.class) 	//@LastModifiedDate --> 2)
public class BoardEntity {
	
	@Id
	@Column(name="board_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardSeq;
	
	@Column(name="board_writer", nullable = false)
	private String boardWriter;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="board_content")
	private String boardContent;
	
	@Column(name="hit_count")
	private int hitCount;
	
	@Column(name="create_date")
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@Column(name="update_date")
	@LastModifiedDate	//--> 1)
	private LocalDateTime updateDate;
	
	@Column(name="original_file_name")
	private String originalFileName;
	
	@Column(name="saved_file_name")
	private String savedFileName;
	
	//OneToMany
	//many를 받아와서 list로
	@OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE)	//자식(상대편)의 변수명
	private List<ReplyEntity> replyEntity= new ArrayList<>();
	
	//reply 개수
	@Formula("(select count(1) from reply r where board_seq = r.board_seq)")
	private int replyCount;
	
	//dto -> entity (@Builder를 이용함)
	//클라이언트 -> 서버
	public static BoardEntity toEntity(BoardDTO boardDTO) {
		return BoardEntity.builder()
				.boardSeq(boardDTO.getBoardSeq())
				.boardWriter(boardDTO.getBoardWriter())
				.boardTitle(boardDTO.getBoardTitle())
				.boardContent(boardDTO.getBoardContent())
				.hitCount(boardDTO.getHitCount())
				.originalFileName(boardDTO.getOriginalFileName())
				.savedFileName(boardDTO.getSavedFileName())
				.build();
	}
	
}
