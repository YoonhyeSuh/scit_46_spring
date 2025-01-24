package net.scit.review.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.review.dto.MovieDTO;
import net.scit.review.dto.ReviewDTO;
import net.scit.review.entity.MovieEntity;
import net.scit.review.entity.ReviewEntity;
import net.scit.review.repository.MovieRepository;
import net.scit.review.repository.ReviewRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {

	private final MovieRepository movieRepository;
	private final ReviewRepository reviewRepository;
	
	//영화 정보 전체를 리스트로 변환하여 반환
	public List<MovieDTO> selectAll() {
		List<MovieEntity> entityList = movieRepository.findAll();
		List<MovieDTO> dtoList = new ArrayList<>();
		for(MovieEntity entity : entityList) {
			dtoList.add(MovieDTO.toDTO(entity));
		}
		return dtoList;
	}

	//dto에 담긴 영화 정보를 entity로 변환하여 db에 저장
	public void insertMovie(MovieDTO movieDTO) {
		MovieEntity entity = MovieEntity.toEntity(movieDTO);
		movieRepository.save(entity);
	}

	//영화 제목 중복 확인
	public boolean existName(String movieName) {
		boolean result = movieRepository.existsByMovieName(movieName);
		return !result;
	}

	//영화 정보 삭제
	@Transactional
	public void deleteOne(Long movieNum) {
		movieRepository.deleteById(movieNum);
	}


	//영화 최신 등록 순으로 조회
	public List<MovieDTO> findAllSortedByDate() {
		List<MovieEntity> entityList = movieRepository.findAll(Sort.by(Sort.Direction.DESC,"movieDate"));
		List<MovieDTO> dtoList = new ArrayList<>();
		for(MovieEntity entity : entityList) {
			dtoList.add(MovieDTO.toDTO(entity));
		}
		return dtoList;
	}


	//영화 장르 순으로 조회
	public List<MovieDTO> findAllSortedByGenreList() {
		List<MovieEntity> entityList = movieRepository.findAll(Sort.by(Sort.Direction.ASC,"genre"));
		List<MovieDTO> dtoList = new ArrayList<>();
		for(MovieEntity entity : entityList) {
			dtoList.add(MovieDTO.toDTO(entity));
		}
		return dtoList;
	}

	//영화 장르를 기준으로 조회
	public List<MovieDTO> getMoviesByGenre(String genre) {
		List<MovieEntity> movies = movieRepository.findByGenre(genre);
        return movies.stream().map(MovieDTO::toDTO).collect(Collectors.toList());
	}

	//영화 리뷰 등록
	//영화 번호를 사용하여 해당 영화 정보를 조회하고 리뷰 저장
	public void insertReview(ReviewDTO reviewDTO) {
		Optional<MovieEntity> temp = movieRepository.findById(reviewDTO.getMovieNum());
		if(!temp.isPresent()) return;
		
		MovieEntity movieEntity = temp.get();
		
		ReviewEntity reviewEntity = ReviewEntity.toEntity(reviewDTO, movieEntity);
		reviewRepository.save(reviewEntity);
	}

	//영화 번호를 통해 해당 영화 정보 조회
	public MovieDTO selectOne(Long movieNum) {
		Optional<MovieEntity> temp = movieRepository.findById(movieNum);
		if(temp.isEmpty()) return null;
		MovieEntity movieEntity = temp.get();
		MovieDTO movieDTO = MovieDTO.toDTO(movieEntity);
		return movieDTO;
	}

	//해당 영화 정보에 대한 리뷰 리스트 조회
	public List<ReviewDTO> reviewList(Long movieNum) {
		Optional<MovieEntity> temp = movieRepository.findById(movieNum);
		if (temp.isEmpty()) return null;
		List<ReviewEntity> reviewEntityList = reviewRepository.findAllByMovieEntity(temp, Sort.by(Sort.Direction.DESC, "reviewDate"));
		List<ReviewDTO> reviewDTOList = new ArrayList<>();
		reviewEntityList.forEach((entity) -> reviewDTOList.add(ReviewDTO.toDTO(entity, movieNum)));
		
		return reviewDTOList;
	}

}
