package net.scit.spring3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class JoinDTO {
	private String username;
	private String userpwd;
	private String email;
	private String gender;
	private String sports;
}
