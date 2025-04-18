package net.scit.spring5.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.spring5.entity.PhoneEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder	//@AllArgsConstructor가 있어야 함
public class PhoneDTO {
	private Integer id;
	private String fname;
	private String phone;
	private LocalDate birthday;
	private Boolean ftype;
	
	public static PhoneDTO toDTO(PhoneEntity phoneEntity) {
		return PhoneDTO.builder()
				.id(phoneEntity.getId())
				.fname(phoneEntity.getFname())
				.phone(phoneEntity.getPhone())
				.birthday(phoneEntity.getBirthday())
				.ftype(phoneEntity.getFtype()).build();
	}
}
