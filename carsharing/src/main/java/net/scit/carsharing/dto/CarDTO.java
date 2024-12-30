package net.scit.carsharing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.entity.CarEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CarDTO {
	Integer carSeq;
	String carType;
	Boolean carStatus;
	
	public static CarDTO toDTO(CarEntity carEntity) {
		return CarDTO.builder()
				.carSeq(carEntity.getCarSeq())
				.carType(carEntity.getCarType())
				.carStatus(carEntity.getCarStatus())
				.build();
	}
}
