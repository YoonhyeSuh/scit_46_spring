package net.scit.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.sec.entity.UserEntity;

//jparepository를 상속받기 때문에 @Repository를 안써도 됨
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	boolean existsByUserId(String userId);

	UserEntity findByUserId(String userId);
	
}
