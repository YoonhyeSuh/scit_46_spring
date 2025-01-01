package net.scit.carsharing.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
	
	@Transactional
	public boolean reservated(Integer carSeq, String userId) {
		
		Optional<CarEntity> carOptional = carRepository.findById(carSeq);
			
		 if (carOptional.isPresent()) {
			//Optional<CarEntity> 내부에 포함된 실제 CarEntity 객체를 반환
			 CarEntity carEntity = carOptional.get();
		      
		    // UserEntity를 userId로 조회
		        Optional<UserEntity> userOptional = userRepository.findById(userId);
		        if (userOptional.isPresent()) {
		            UserEntity userEntity = userOptional.get();

		            //차량 상태 변경
		            boolean newStatus = !carEntity.getCarStatus();
		            carEntity.setCarStatus(newStatus);

		            // OrderEntity 생성 및 저장
		            OrderEntity orderEntity = OrderEntity.builder()
		                .user(userEntity)
		                .car(carEntity)
		                .sharingStatus(newStatus)  
		                .sharingDate(LocalDateTime.now())
		                .build();

		            // 변경 사항 저장
		            carRepository.save(carEntity);
		            orderRepository.save(orderEntity);

		            return true; 
		        } else {
		        	return false;
		        } 
		 } else {
			 return false;
		 }
		
	 }
}
