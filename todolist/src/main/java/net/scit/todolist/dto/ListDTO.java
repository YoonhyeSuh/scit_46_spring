package net.scit.todolist.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
}
