package net.scit.spring7.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.ReplyDTO;
import net.scit.spring7.entity.BoardEntity;
import net.scit.spring7.entity.ReplyEntity;
import net.scit.spring7.repository.BoardRepository;
import net.scit.spring7.repository.ReplyRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyService {
	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	/**
	 * 전달받은 값을 entity로 수정한 후에 DB에 저장
	 * @param replyDTO
	 */
	public void insert(ReplyDTO replyDTO) {	//service쪽으로 넘어온 replyDTO를 entity로 바꿔서 db저장
		//1) 부모글이 있는지 조회
		Optional<BoardEntity> temp = boardRepository.findById(replyDTO.getBoardSeq());
		if(!temp.isPresent()) return;
		
		//2) 부모글을 꺼냄
		BoardEntity boardEntity = temp.get();
		
		//3) 두개를 전달해서 entity를 반환
		ReplyEntity replyEntity = ReplyEntity.toEntity(replyDTO, boardEntity);
		
		//3) DB에 저장(save)
		replyRepository.save(replyEntity);
	}
	
	/**
	 * boardSeq에 해당하는 댓글 전체 조회
	 * @param boardSeq
	 * @return
	 */
	public List<ReplyDTO> replyAll(Long boardSeq) {
	
		//1) 부모글이 있는지 조회
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);
		
		//2) 댓글 조회를 위한 Query Method
		//boardEntity에 List<ReplyEntity>가 있기 때문에 목록 반환 replyEntity가 아님!!
		List<ReplyEntity> entityList = replyRepository.findAllByBoardEntity(temp, Sort.by(Sort.Direction.DESC, "replySeq")); //temp = boardEntity
		
		//log.info("댓글 개수: {}", entityList.size());
		
		//3) List<ReplyDTO>를 선언
		List<ReplyDTO> list = new ArrayList<>();
		//Entity -> DTO
		entityList.forEach((entity) -> list.add(ReplyDTO.toDTO(entity, boardSeq)));	//entity만 전달시 boardSeq없음
		
		return list;
	}

	/**
	 * 댓글 삭제처리
	 * @param replySeq
	 */
	public void replyDelete(Long replySeq) {
		Optional<ReplyEntity> temp =replyRepository.findById(replySeq);	//한개 데이터 반환 optional
		
		if(!temp.isPresent()) return;
		
		replyRepository.deleteById(replySeq);
	}

	/**
	 * 댓글 조회
	 * @param replySeq
	 * @return
	 */
	public ReplyDTO replySelectOne(Long replySeq) {
		Optional<ReplyEntity> temp =replyRepository.findById(replySeq);	//한개 데이터 반환 optional
		
		if(!temp.isPresent()) return null;
		
		ReplyEntity entity = temp.get();
		
		ReplyDTO replyDTO = ReplyDTO.toDTO(entity, entity.getBoardEntity().getBoardSeq());
		
		return replyDTO;
	}

	@Transactional
	public void updateProc(ReplyDTO replyDTO) {
		
		
	}
}
