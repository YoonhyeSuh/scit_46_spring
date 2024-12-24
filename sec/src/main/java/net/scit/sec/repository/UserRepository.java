package net.scit.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.sec.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	boolean existsByUserId(String userId);
	
}
