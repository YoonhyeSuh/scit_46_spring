package net.scit.carsharing.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
	Integer orderSeq;
	
	@OneToOne
	@JoinColumn(name="user_id", referencedColumnName = "user_id")
	private UserEntity user;
	
	@OneToOne
	@JoinColumn(name="car_id", referencedColumnName = "car_seq")
	private CarEntity car;
	
	@Column(name="sharing_status")
	Boolean sharingStatus;
	
	@Column(name="sharing_date")
	LocalDate sharingDate;
	
	public static OrderEntity toEntity(OrderDTO orderDTO) {
		return OrderEntity.builder()
				.orderSeq(orderDTO.getOrderSeq())
				.sharingStatus(orderDTO.getSharingStatus())
				.sharingDate(orderDTO.getSharingDate())
				.build();
	}
}
