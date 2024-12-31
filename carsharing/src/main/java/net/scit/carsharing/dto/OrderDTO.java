package net.scit.carsharing.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.entity.OrderEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderDTO {
	private Integer orderSeq;
	private String userId;
	private Integer carId;
	private Boolean sharingStatus;
	private LocalDate sharingDate;
	
	public static OrderDTO toDTO(OrderEntity orderEntity) {
		return OrderDTO.builder()
				.orderSeq(orderEntity.getOrderSeq())
				.userId(orderEntity.getUser().getUserId())
				.carId(orderEntity.getCar().getCarSeq())
				.sharingStatus(orderEntity.getSharingStatus())
				.sharingDate(orderEntity.getSharingDate())
				.build();
	}
}
