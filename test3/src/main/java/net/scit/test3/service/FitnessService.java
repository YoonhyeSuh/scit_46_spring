package net.scit.test3.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.test3.dto.FitnessDTO;
import net.scit.test3.entity.FitnessEntity;
import net.scit.test3.repository.FitnessRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class FitnessService {
	private final FitnessRepository fitnessRepository;
	
	public void insert(FitnessDTO fitnessDTO) {
		//dto -> entity
		//fitnessRepository 인터페이스를 작성 --> JpaRepository를 상속
		//repository.save(entity)를 하여 DB에 저장
		FitnessEntity fitnessEntity = FitnessEntity.toEntity(fitnessDTO);
		fitnessRepository.save(fitnessEntity);
	}
	
}
