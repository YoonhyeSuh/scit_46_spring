package net.scit.hw_cashbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.hw_cash.dto.CashBookDTO;
import net.scit.hw_cashbook.entity.CashBookEntity;
import net.scit.hw_cashbook.repository.CashBookRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashBookService {
	
	private final CashBookRepository cashBookRepository;

	public void insert(CashBookDTO cashBookDTO) {
		//dto -> entity
		//CashBookRepository 인터페이스를 작성 --> JpaRepository를 상속
		//repository.save(entity)를 하여 DB에 저장
		
		CashBookEntity cashBookEntity = CashBookEntity.toEntity(cashBookDTO);
		cashBookRepository.save(cashBookEntity);
	}

	public void delete(Long cashSeq) {
		cashBookRepository.deleteById(cashSeq);
	}

	public List<CashBookDTO> selectAll() {
		List<CashBookEntity> entityList = cashBookRepository.findAll(Sort.by(Sort.Direction.ASC,"regdate"));
		List<CashBookDTO> dtoList = new ArrayList<>();
		
		for(CashBookEntity entity : entityList) {
			dtoList.add(CashBookDTO.toDTO(entity));
		}
		return dtoList;
	}

}
