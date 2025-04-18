package net.scit.spring5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring5.dto.PhoneDTO;
import net.scit.spring5.entity.PhoneEntity;
import net.scit.spring5.repository.PhoneRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhoneService {
	
	private final PhoneRepository phoneRepository;
	
	public void insert(PhoneDTO phoneDTO) {
		log.info("----------------{}", phoneDTO.toString());
		
		PhoneEntity phoneEntity = PhoneEntity.toEntity(phoneDTO);
		
		phoneRepository.save(phoneEntity);	//전달인자로 반드시 entity 타입이 와야 함
	}

	//전달받은 아이디의 정보를 DB에서 조회한다.
	//@param id (PK)
	//@return 조회된 entity를 dto로 변환해서 반환
	public PhoneDTO selectOne(Integer id) {
		Optional<PhoneEntity> temp = phoneRepository.findById(id);
		
		if(temp.isPresent()) {
			PhoneEntity entity = temp.get();
			
			//entity를 dto로 변경
			PhoneDTO phoneDTO = PhoneDTO.toDTO(entity);
			return phoneDTO;
		}
		return null;
	}

	//전달받은 id를 DB에서 삭제하는 repository에 요청
	//@param id
	public void delete(Integer id) {
		phoneRepository.deleteById(id);
	}

	@Transactional
	public void update(PhoneDTO phoneDTO) {
		
		//jpa에서 데이터 수정하려면 select 먼저 하라고 함
		//수정을 할 때는 그냥 save() 하지 말고, findById로 데이터가 있는지 확인부터 해라
		Optional<PhoneEntity> temp = phoneRepository.findById(phoneDTO.getId());
		
		if(temp.isPresent()) {
			PhoneEntity entity = PhoneEntity.toEntity(phoneDTO);
			phoneRepository.save(entity);
		}
	}

	//전화번호부의 모든 데이터를 DB에서 조회
	//@return List<PhoneDTO>
	public List<PhoneDTO> selectAll() {
		List<PhoneEntity> entityList = phoneRepository.findAll(Sort.by(Sort.Direction.ASC, "fname"));
		List<PhoneDTO> dtoList = new ArrayList();	//객체 생성
		
		//1) for문 (advanced for)
		for(PhoneEntity entity : entityList) {
			dtoList.add(PhoneDTO.toDTO(entity));
		}
		
		//2) lambda 식 : 전달인자 -> 반환 , 타입 추론
		//entityList.forEach((entity) -> dtoList.add(PhoneDTO.toDTO(entity)));
		return dtoList;
	}

	
}
