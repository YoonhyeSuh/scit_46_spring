package net.scit.test3.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.scit.test3.dto.FitnessDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="fitness")
public class FitnessEntity {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private Boolean gender;
	
	@Column(name="height")
	private double height;
	
	@Column(name="weight")
	private double weight;

	@Column(name="join_date")
	private LocalDate joinDate;
	
	public static FitnessEntity toEntity(FitnessDTO fitnessDTO) {
		return FitnessEntity.builder()
				.id(fitnessDTO.getId())
				.name(fitnessDTO.getName())
				.gender(fitnessDTO.getGender())
				.height(fitnessDTO.getHeight())
				.weight(fitnessDTO.getWeight())
				.build();
	}
}
