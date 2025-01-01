package net.scit.carsharing.dto;

import java.time.LocalDateTime;

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
	private String carType;
	private Boolean sharingStatus;
	private LocalDateTime sharingDate;
	
	public static OrderDTO toDTO(OrderEntity orderEntity) {
		return OrderDTO.builder()
				.orderSeq(orderEntity.getOrderSeq())
                .userId(orderEntity.getUser() != null ? orderEntity.getUser().getUserId() : null)
                .carId(orderEntity.getCar() != null ? orderEntity.getCar().getCarSeq() : null)
                .carType(orderEntity.getCar() != null ? orderEntity.getCar().getCarType() : null)
                .sharingStatus(orderEntity.getSharingStatus())
                .sharingDate(orderEntity.getSharingDate())
                .build();
	}
}
