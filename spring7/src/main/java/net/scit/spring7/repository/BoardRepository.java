package net.scit.spring7.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.spring7.entity.BoardEntity;
import net.scit.spring7.entity.ReplyEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	//검색기능을 위한 쿼리 메소드 선
	List<BoardEntity> findByBoardTitleContains(String searchWord, Sort sort);

	List<BoardEntity> findByBoardWriterContains(String searchWord, Sort sort);

	List<BoardEntity> findByBoardContentContains(String searchWord, Sort sort);

}
