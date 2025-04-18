package net.scit.guestbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.scit.guestbook.dto.GuestbookDTO;
import net.scit.guestbook.entity.GuestbookEntity;
import net.scit.guestbook.repository.GuestbookRepository;

@Service
@RequiredArgsConstructor
public class GuestbookService {
	private final GuestbookRepository guestbookRepository;
	/**
	 * 전달받은 dto 객체를 entity로 변환 후 DB에 저장
	 * @param guestbookDTO
	 */
	public void insert(GuestbookDTO guestbookDTO) {
		GuestbookEntity entity = GuestbookEntity.toEntity(guestbookDTO);
		guestbookRepository.save(entity);
	}
	
	/**
	 * DB의 모든 데이터를 날짜별 역순으로 조회하여 반환
	 * @return
	 */
	public List<GuestbookDTO> selectAll() {
		// 컬럼명이 아니라 Entity의 멤버명으로 문자열 내에 기재
		List<GuestbookEntity> temp = guestbookRepository.findAll(Sort.by(Sort.Direction.DESC, "regdate"));
		List<GuestbookDTO> list = new ArrayList<>();
		
		temp.forEach((entity) -> list.add(GuestbookDTO.toDTO(entity)));
		return list;
	}

	/**
	 * 데이터 삭제
	 * @param guestbookDTO
	 */
	@Transactional
	public void delete(GuestbookDTO guestbookDTO) {
		//퀴리 메소드 : JPA가 제공하는 기본 메소드로는 부족할 때
		//개발자가 정해진 규칙에 맞게 메소드 명을 만들어 퀴리가 작성되도록 하는 문법
		guestbookRepository.deleteBySeqnoAndGuestPwd(guestbookDTO.getSeqno(), guestbookDTO.getGuestPwd());
		
	}

}
