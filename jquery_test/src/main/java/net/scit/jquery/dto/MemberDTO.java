package net.scit.jquery.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//builder 쓸 때만 allargsconstructor가 필요
@Setter
@Getter
@ToString
public class MemberDTO {
	private String userid;
	private String email;
	private int age;
	private String location;
}
