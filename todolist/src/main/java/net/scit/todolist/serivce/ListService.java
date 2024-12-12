package net.scit.todolist.serivce;

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
	
}
