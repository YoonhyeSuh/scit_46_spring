package net.scit.carsharing.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.carsharing.dto.CarDTO;
import net.scit.carsharing.entity.CarEntity;
import net.scit.carsharing.entity.OrderEntity;
import net.scit.carsharing.entity.UserEntity;
import net.scit.carsharing.repository.CarRepository;
import net.scit.carsharing.repository.OrderRepository;
import net.scit.carsharing.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {

	private final UserRepository userRepository;
	private final CarRepository carRepository;
	private final OrderRepository orderRepository;

	public List<CarDTO> selectAll() {
		List<CarEntity> entityList = carRepository.findAll(Sort.by(Sort.Direction.ASC,"carSeq"));
		List<CarDTO> dtoList = new ArrayList<>();
		for(CarEntity entity : entityList) {
			dtoList.add(CarDTO.toDTO(entity));
		}
		return dtoList;
	}
	
	public boolean reservated(String userId, Integer carSeq) {
		Optional<UserEntity> userOptional = userRepository.findById(userId);
		Optional<CarEntity> carOptional = carRepository.findById(carSeq);
		
		if(userOptional.isPresent() && carOptional.isPresent()) {
			//Optional<CarEntity> 내부에 포함된 실제 CarEntity 객체를 반환
			CarEntity carEntity = carOptional.get();
			UserEntity userEntity = userOptional.get();
			
			boolean newStatus = !carEntity.getCarStatus();
            carEntity.setCarStatus(newStatus);
			
			OrderEntity orderEntity = OrderEntity.builder()
					.user(userEntity)
					.car(carEntity)
					.sharingStatus(newStatus)
					.sharingDate(LocalDate.now())
					.build();
			
			carRepository.save(carEntity);
			orderRepository.save(orderEntity);
			return true;
		} else {
			return false;
		}
	}
}
