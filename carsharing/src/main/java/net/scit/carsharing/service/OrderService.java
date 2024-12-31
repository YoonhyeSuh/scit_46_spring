package net.scit.carsharing.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.scit.carsharing.repository.CarRepository;
import net.scit.carsharing.repository.OrderRepository;
import net.scit.carsharing.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final CarRepository carRepository;
	private final UserRepository userRepository;
	
	
}
