package net.scit.spring6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.spring6.entity.ReadingNoteEntity;

public interface ReadingNoteRepository extends JpaRepository<ReadingNoteEntity, Integer>{

}
