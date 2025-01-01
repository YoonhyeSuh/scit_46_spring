package net.scit.carsharing.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.carsharing.dto.CarDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="sharing_car")
public class CarEntity {
	
	@Id
	@Column(name="car_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer carSeq;
	
	@Column(name="car_type", nullable = false)
	private String carType;
	
	@Column(name="car_status")
	private Boolean carStatus;
	
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	private List<OrderEntity> order;
	
	public static CarEntity toEntity(CarDTO carDTO) {
		return CarEntity.builder()
				.carSeq(carDTO.getCarSeq())
				.carType(carDTO.getCarType())
				.carStatus(carDTO.getCarStatus())
				.build();
	}
}
