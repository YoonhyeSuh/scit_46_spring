package net.scit.carsharing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.scit.carsharing.dto.OrderDTO;
import net.scit.carsharing.entity.CarEntity;
import net.scit.carsharing.entity.OrderEntity;
import net.scit.carsharing.entity.UserEntity;
import net.scit.carsharing.repository.CarRepository;
import net.scit.carsharing.repository.OrderRepository;
import net.scit.carsharing.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final CarRepository carRepository;
	
	public List<OrderDTO> orderSelectAll(String userId) {
		List<OrderEntity> entityList = orderRepository.findByUser_UserId(userId, Sort.by(Sort.Direction.DESC, "orderSeq"));
		List<OrderDTO> dtoList = new ArrayList<>();
		entityList.forEach((entity) -> dtoList.add(OrderDTO.toDTO(entity)));
		return dtoList;
	}
	
	@Transactional
	public boolean returnCar(Integer orderSeq, Integer carSeq, String userId) {
		Optional<OrderEntity> orderOptional = orderRepository.findById(orderSeq);
		if (orderOptional.isPresent()) {

			OrderEntity orderEntity = orderOptional.get();

			Optional<CarEntity> carOptional = carRepository.findById(carSeq);
			CarEntity carEntity = carOptional.get();

			boolean newOrderStatus = !orderEntity.getSharingStatus();
			orderEntity.setSharingStatus(newOrderStatus);

			boolean newStatus = !carEntity.getCarStatus();
			carEntity.setCarStatus(newStatus);

			carRepository.save(carEntity);
			orderRepository.save(orderEntity);
			return true;
		}

		return false;
	}
}
