package net.scit.spring7.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.BoardDTO;
import net.scit.spring7.entity.BoardEntity;
import net.scit.spring7.repository.BoardRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
	private final BoardRepository boardRepository;
	
	public List<BoardDTO> selectAll(){
		List<BoardEntity> temp = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
		List<BoardDTO> list = new ArrayList<>();
		
		log.info("============= 총 글개수: {}", temp.size());
		
		temp.forEach((entity) -> list.add(BoardDTO.toDTO(entity)));
		return list;
	}
	
	/**
	 * 전달받은 BoardDTO를 DB에 저장
	 * @param boardDTO
	 */
	public void insertBoard(BoardDTO boardDTO) {
		BoardEntity entity = BoardEntity.toEntity(boardDTO);
		
		boardRepository.save(entity);
	}

	/**
	 * boardSeq에 해당하는 글을 조회
	 * @param boardSeq
	 * @return
	 */
	public BoardDTO selectOne(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq); //findById는 Optional로 반환
		
		//조회된 결과를 꺼내서 dto로 반환
		/*if(temp.isPresent()) return null;	//데이터가 없으면 null 반환
		BoardEntity entity = temp.get();
		return BoardDTO.toDTO(entity);*/
		
		return BoardDTO.toDTO(temp.get());
	}

	/*
		boardSeq레코드 조회수 증가
	 	jpa는 update() 메소드가 따로 없음
		save() -> 기존에 있던 값을 꺼내서 1을 증가하고 다시 집어 넣는다
	 	update from board set hitcount = hitcount+1 where board_seq = ?;
	  
	  	save() : 새롭게 데이터를 imsert
	  		  : 기존 데이터일 경우 update
	  	그 기준이 pk(pk가 중요)
	  
	  	하나의 트랜잭션내에서 pk로 조회한 후 set을 하면서 값을 바꾸면 update
	  
	  	delete from board
	  	where board_seq = ?;
	 */
	
	/**
	 * 1) 조회 : findById(boardSeq)
	 * 2) hitCount를 get한후 +1 ==> set
	 * @param boardSeq
	 */
	
	@Transactional
	public void incrementHitcount(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);
		
		if(!temp.isPresent()) return;
		BoardEntity boardEntity = temp.get();
		
		int hitcount = boardEntity.getHitCount() + 1; //get 꺼내진 데이터 타입은 entity
		boardEntity.setHitCount(hitcount);
	}

	/**
	 * DB에 삭제 요청
	 * @param boardSeq
	 */
	@Transactional
	public void deleteOne(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);
		
		if(!temp.isPresent()) return;
		boardRepository.deleteById(boardSeq);
	}

	/*
		수정
		1) 목록에서 수정요청시 --> boardSeq를 request param으로 보내서 조회
		2) 조회된 Board 결과값을 model에 저장하여 controller에 넣는다
		3) 수정화면에서 2)의 데이터를 출력 input 내에서 출력하면 된다
		4) 사용자가 값을 수정하고, 그 갑으로 수정 요청을 보냄
		
		update
	        board 
	    set
	        board_content=????,
	        board_title=????,
	        board_writer=?,	//콘트롤러에서 시큐리티 세션에 접근한 후에 그 값으로 세팅
	        create_date=?,
	        hit_count=?,
	        update_date=? 
	    where
	        board_seq=????
	 */
	
	/**
	 * DB에 수정처리
	 * @param boardDTO
	 */
	@Transactional
	public void updateBoard(BoardDTO boardDTO) {
		//1) 수정하려는 데이터가 있는지 확인
		Optional<BoardEntity> temp = boardRepository.findById(boardDTO.getBoardSeq());
		
		if(!temp.isPresent()) return;	//없으면 return
		//2) 있으면 dto->entity로 변환
//		BoardEntity entity = BoardEntity.toEntity(boardDTO);
//		boardRepository.save(entity); 	//모든 값이 set됨
		
		BoardEntity boardEntity = temp.get();
		boardEntity.setBoardTitle(boardDTO.getBoardTitle());
		boardEntity.setBoardContent(boardDTO.getBoardContent());
		
		boardRepository.save(boardEntity);
	
	}
	
	
}


