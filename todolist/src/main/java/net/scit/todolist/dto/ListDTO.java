package net.scit.todolist.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.todolist.entity.ListEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ListDTO {
	private int seqno;
	private LocalDate regdate;
	private String status;
	private String importance;
	private String categories;
	private String todo;
	
	public static ListDTO toDTO(ListEntity listEntity) {
		return ListDTO.builder()
				.seqno(listEntity.getSeqno())
				.regdate(listEntity.getRegdate())
				.status(listEntity.getStatus())
				.importance(listEntity.getImportance())
				.categories(listEntity.getCategories())
				.todo(listEntity.getTodo())
				.build();
	}
}
