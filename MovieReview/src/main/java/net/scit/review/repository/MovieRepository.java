package net.scit.review.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.review.entity.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

	boolean existsByMovieName(String movieName);
	List<MovieEntity> findByGenre(String genre);

}
