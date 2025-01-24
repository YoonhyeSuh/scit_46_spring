package net.scit.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import net.scit.review.entity.MovieEntity;
import net.scit.review.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

	List<ReviewEntity> findAllByMovieEntity(Optional<MovieEntity> temp, Sort by);

}
