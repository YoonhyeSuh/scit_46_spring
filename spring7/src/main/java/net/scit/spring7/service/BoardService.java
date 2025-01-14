package net.scit.spring7.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring7.dto.BoardDTO;
import net.scit.spring7.entity.BoardEntity;
import net.scit.spring7.repository.BoardRepository;
import net.scit.spring7.util.FileService;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
	private final BoardRepository boardRepository;
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@Value("${user.board.pageLimit}")
	private int pageLimit;
	/**
	 * 1) 단순조회: 게시글 전체 목록 조회
	 * 2) 검색조회: 쿼리 메소드 사용
	 * 3)페이징 추가
	 * @param pageable 
	 * @param searchItem : boardTitle findByBoardTitleContains("쥐")
	 * @param searchWord
	 * @return
	 */
	
	public Page<BoardDTO> selectAll(Pageable pageable, String searchItem, String searchWord){
		
		// -1을 한 이유 : DB의 Page는 0부터 시작함. 사용자는 1을 요청하기 때문
		//사용자가 요청한 페이지 번호
		int pageNumber = pageable.getPageNumber()-1;
		
		Page<BoardEntity> temp = null;
		
		switch(searchItem){
			case "boardTitle": 
				temp = boardRepository.findByBoardTitleContains(searchWord, PageRequest.of(pageNumber, pageLimit, Sort.by(Sort.Direction.DESC, "createDate")));
				break;
			case "boardWriter":
				temp = boardRepository.findByBoardWriterContains(searchWord, PageRequest.of(pageNumber, pageLimit, Sort.by(Sort.Direction.DESC, "createDate")));
				break;
			case "boardContent": 
				temp = boardRepository.findByBoardContentContains(searchWord, PageRequest.of(pageNumber, pageLimit, Sort.by(Sort.Direction.DESC, "createDate")));
				break;
		}
		
		//1) 단순조회
		//List<BoardEntity> temp = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
		Page<BoardDTO> list = null;
		list = temp.map((entity) -> BoardDTO.toDTO(entity));
		
		/*
		log.info("getSize: {}", list.getSize());										//한 페이지당 글개수 
		log.info("getTotalElements: {}", list.getTotalElements());						//전체 글개수
		log.info("getTotalPages: {}", list.getTotalPages());							//총 페이지 수
		log.info("getNumber: {}", list.getNumber());									//요청한 페이지
		log.info("getNumberOfElements: {}", list.getNumberOfElements());				//현재 페이지의 총 글개수
		log.info("isFirst: {}", list.isFirst());										//첫번째 페이지니?
		log.info("isLast: {}", list.isLast());											//마지막 페이지니?	
		log.info("getContent().get(0): {}", list.getContent().get(0).toString());		//
		*/
		
		return list;
		
		//log.info("============= 총 글개수: {}", temp.size());
		//temp.forEach((entity) -> list.add(BoardDTO.toDTO(entity)));
		//2) Lambda 객체, Stream : List, Set, Map / 중간연산, 최종연산
	}
	
	/**
	 * 1) 단순조회: 게시글 전체 목록 조회
	 * 2) 검색조회: 쿼리 메소드 사용
	 * @param searchItem : boardTitle findByBoardTitleContains("쥐")
	 * @param searchWord
	 * @return
	 */
	
//	public List<BoardDTO> selectAll(String searchItem, String searchWord){
//		
//		//2) 검색조회
//		List<BoardEntity> temp = null;
//		
//		switch(searchItem){
//			case "boardTitle": 
//				temp = boardRepository.findByBoardTitleContains(searchWord, Sort.by(Sort.Direction.DESC, "createDate"));
//				break;
//			case "boardWriter":
//				temp = boardRepository.findByBoardWriterContains(searchWord, Sort.by(Sort.Direction.DESC, "createDate"));
//				break;
//			case "boardContent": 
//				temp = boardRepository.findByBoardContentContains(searchWord, Sort.by(Sort.Direction.DESC, "createDate"));
//				break;
//		}
//		
//		
//		
//		
//		//1) 단순조회
//		//List<BoardEntity> temp = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createDate"));
//		List<BoardDTO> list = new ArrayList<>();
//		
//		//log.info("============= 총 글개수: {}", temp.size());
//		
//		temp.forEach((entity) -> list.add(BoardDTO.toDTO(entity)));
//		return list;
//	}
	
	/**
	 * 전달받은 BoardDTO를 DB에 저장
	 * 첨부파일 여부에 따라 DB의 두 컬럼값을 수정
	 * @param boardDTO
	 */
	public void insertBoard(BoardDTO boardDTO) {
		MultipartFile uploadFile = boardDTO.getUploadFile();
		String savedFileName = null;
		String originalFileName = null;
		
		if(!uploadFile.isEmpty()) {//비워있지 않으면 실행
			savedFileName = FileService.saveFile(boardDTO.getUploadFile(), uploadPath);
			originalFileName = uploadFile.getOriginalFilename();
		}
		
		boardDTO.setSavedFileName(savedFileName);
		boardDTO.setOriginalFileName(originalFileName);
		
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
	  	
	  	조회수 증가시 줭이 일어남 ==> incrementHitcount() ==> 단순 조회에서 실행되는 코드
	  							자신의 글은 조회수 증가를 하면 안됨
	  	게시글 수정 시 수정이 일어남 ==> boardUpdate()
	  	
	  	@LastModifiedDate : 수정이 일어나면 새로운 값이 세팅됨
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
		
		//temp를 뒤져서 savedfilename이 존재하면 물리적으로 삭제
		String savedFileName = temp.get().getSavedFileName();
		if(savedFileName != null) {
			String fullPath = uploadPath + "/" + savedFileName;
			FileService.deleteFile(fullPath);
		}
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
		
		//1) 첨부파일이 있는지 확인
		MultipartFile file = boardDTO.getUploadFile();
		
		String newFile = !file.isEmpty() ? file.getOriginalFilename() : null;	//originalfilename..
		
		
		
		//1) 수정하려는 데이터가 있는지 확인
		Optional<BoardEntity> temp = boardRepository.findById(boardDTO.getBoardSeq());
		
		if(!temp.isPresent()) return;	//없으면 return
		
		//2) 있으면 dto->entity로 변환
		BoardEntity boardEntity = temp.get();
		
		//기존 파일이 DB에 저장되어 있는지 확인 savedFileName
		String oldFile = boardEntity.getSavedFileName();		//기존에 저장된 파일
		
		//(1) 기존 파일이 있고 업로드한 파일도 있다면 --> 하드 디스크에서는 기존 파일을 삭제, 업로드한 파일을 저장(물리적)
		//								   --> DB에는 업로드한 파일로 두개의 컬럼을 업데이트
		//(2) 기존 파일이 없고 업로드한 파일만 있다면 --> 하드 디스크에서는 업로드한 파일을 저장(물리적)
		//								   --> DB에는 업로드한 파일로 두개의 컬럼을 업데이트
		
		// 업로드한 파일이 있다면		
		if(newFile != null) {
			String savedFileName = null;
			savedFileName = FileService.saveFile(file, uploadPath);
			boardEntity.setSavedFileName(savedFileName);
			boardEntity.setOriginalFileName(newFile);
			
			if(oldFile != null && newFile != null) {
				String fullPath = uploadPath + "/" + oldFile;
				FileService.deleteFile(fullPath);
			}
		}
		
//		BoardEntity entity = BoardEntity.toEntity(boardDTO);
//		boardRepository.save(entity); 	//모든 값이 set됨
		
		boardEntity.setBoardTitle(boardDTO.getBoardTitle());
		boardEntity.setBoardContent(boardDTO.getBoardContent());
		//boardEntity.setUpdateDate(LocalDateTime.now()); @LastModifiedDate를 안쓰고 수정 시간 업데이트
		//boardRepository.save(boardEntity);
	
	}
	
	/**
	 * file 명이 들어있는 2개의 컬럼의 값을 null로
	 * @param boardSeq
	 */
	@Transactional
	public void deleteFile(Long boardSeq) {
		Optional<BoardEntity> temp = boardRepository.findById(boardSeq);
		
		if(temp.isPresent()) {
			BoardEntity entity = temp.get();
			
			entity.setOriginalFileName(null);
			entity.setSavedFileName(null);
		}
	}
}


