package net.scit.todolist.serivce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.todolist.dto.ListDTO;
import net.scit.todolist.entity.ListEntity;
import net.scit.todolist.repository.ListRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListService {
	private final ListRepository listRepository;
	
	public void insert(ListDTO listDTO) {
		ListEntity listEntity = ListEntity.toEntity(listDTO);
		listRepository.save(listEntity);
	}
	
	public void delete(int seqno) {
		listRepository.deleteById(seqno);
	}
	
	public List<ListDTO> selectAll() {
		List<ListEntity> entityList = listRepository.findAll(Sort.by(Sort.Direction.DESC,"regdate"));
		List<ListDTO> dtoList = new ArrayList<>();
		for(ListEntity entity : entityList) {
			dtoList.add(ListDTO.toDTO(entity));
		}
		return dtoList;
	}

	public List<ListDTO> selectChoice(String importance, String categories) {
		List<ListEntity> temp = listRepository.findByImportanceAndCategories(importance, categories, Sort.by(Sort.Direction.DESC,"regdate"));
		List<ListDTO> list = new ArrayList<>();
		temp.forEach((entity) -> list.add(ListDTO.toDTO(entity)));
		return list;
	}
}
