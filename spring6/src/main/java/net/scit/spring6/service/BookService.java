package net.scit.spring6.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.scit.spring6.dto.BookDTO;
import net.scit.spring6.entity.BookEntity;
import net.scit.spring6.repository.BookRepository;
import net.scit.spring6.repository.ReadingNoteRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;
	private final ReadingNoteRepository readingNoteRepository;
	
	public List<BookDTO> selectAll() {
		List<BookEntity> entityList = bookRepository.findAll(Sort.by(Sort.Direction.ASC,"bookSeq"));
		List<BookDTO> dtoList = new ArrayList<>();
		for(BookEntity entity : entityList) {
			dtoList.add(BookDTO.toDTO(entity));
		}
		return dtoList;
	}

	public void insert(BookDTO bookDTO) {
		BookEntity bookEntity = BookEntity.toEntity(bookDTO);
		bookRepository.save(bookEntity);
	}

	public void delete(Integer bookSeqno) {
		bookRepository.deleteById(bookSeqno);
	}

}
