package net.scit.carsharing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.CarDTO;
import net.scit.carsharing.entity.CarEntity;
import net.scit.carsharing.repository.CarRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {

	private final CarRepository carRepository;

	public List<CarDTO> selectAll() {
		List<CarEntity> entityList = carRepository.findAll(Sort.by(Sort.Direction.ASC,"carSeq"));
		List<CarDTO> dtoList = new ArrayList<>();
		for(CarEntity entity : entityList) {
			dtoList.add(CarDTO.toDTO(entity));
		}
		return dtoList;
	}

}
