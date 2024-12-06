package net.scit.hw_cashbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.hw_cashbook.entity.CashBookEntity;

public interface CashBookRepository extends JpaRepository<CashBookEntity, Long>{

}
