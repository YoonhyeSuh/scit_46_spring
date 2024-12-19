package net.scit.hrjoin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.scit.hrjoin.dto.EmployeeDTO;
import net.scit.hrjoin.entity.EmployeeEntity;
import net.scit.hrjoin.repository.EmployeeRepository;


@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository repository;
	
	/* 원래는 Entity 타입을 반환하면 안됨
	 * DTO로 반환해야 함.. 지금은 연습이니까~~
	 */
	public List<EmployeeDTO> selectAll(){	//전체 데이터 조회
		List<EmployeeEntity> temp =  repository.findAll(Sort.by(Sort.Direction.ASC,"firstName"));
		List<EmployeeDTO> dtoList = new ArrayList<>();
		
		for(EmployeeEntity entity : temp) {
			dtoList.add(EmployeeDTO.toDTO(entity));
		}
		
//		String fname = temp.get(58).getFirstName();
//		System.out.print("이름: " + fname);
		
		//String fname = temp.get(58).getDepartment().getDepartmentName(); 부서명이 없기 때문에 error
		return dtoList;
	}
}
