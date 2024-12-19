package net.scit.hrjoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.hrjoin.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
	
}
