package net.scit.todolist.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.todolist.entity.ListEntity;

public interface ListRepository extends JpaRepository<ListEntity, Integer>{

	List<ListEntity> findByImportanceAndCategories(String importance, String categories, Sort by);
	
}
