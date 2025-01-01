package net.scit.carsharing.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.dto.OrderDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="sharing_order")
public class OrderEntity {
	
	@Id
	@Column(name="order_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderSeq;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "user_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name="car_id", referencedColumnName = "car_seq")
	private CarEntity car;

	@Column(name="sharing_status")
	private Boolean sharingStatus;
	
	@Column(name="sharing_date")
	private LocalDateTime sharingDate;
	
	public static OrderEntity toEntity(OrderDTO orderDTO, UserEntity userEntity, CarEntity carentity) {//orderDTO를 가져오면 id값을 찾는 일을 한번 더 해야함 그래서 각 entity를 넣는다
		return OrderEntity.builder()
				.orderSeq(orderDTO.getOrderSeq())
				.sharingStatus(orderDTO.getSharingStatus())
                .sharingDate(LocalDateTime.now())
				.user(userEntity)
				.car(carentity)
				.build();
	}

}
