package net.scit.carsharing.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.carsharing.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

	List<OrderEntity> findByUser_UserId(String userId, Sort by);

}
