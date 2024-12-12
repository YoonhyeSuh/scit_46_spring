package net.scit.todolist.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.todolist.dto.ListDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="todolist")
public class ListEntity {
	@Id
	@Column(name="seqno")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seqno;
	
	@Column(name="regdate")
	@CreationTimestamp
	private LocalDate regdate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="importance")
	private String importance;
	
	@Column(name="categories")
	private String categories;
	
	@Column(name="todo")
	private String todo;
	
	public static ListEntity toEntity(ListDTO listDTO) {
		return ListEntity.builder()
				.seqno(listDTO.getSeqno())
				.regdate(listDTO.getRegdate())
				.status(listDTO.getStatus())
				.importance(listDTO.getImportance())
				.categories(listDTO.getCategories())
				.todo(listDTO.getTodo())
				.build();
	}
}
