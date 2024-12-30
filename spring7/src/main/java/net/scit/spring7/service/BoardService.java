package net.scit.spring7.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
		List<BoardEntity> temp = boardRepository.findAll();
		List<BoardDTO> list = new ArrayList<>();
		
		log.info("============= 총 글개수: {}", temp.size());
		
		temp.forEach((entity) -> list.add(BoardDTO.toDTO(entity)));
		return list;
	}
}
