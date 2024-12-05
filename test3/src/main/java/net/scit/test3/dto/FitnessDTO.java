package net.scit.test3.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FitnessDTO {
	private Long id;
	private String name;
	private double height;
	private double weight;
	private Boolean gender;
	private LocalDate joinDate;
	private double stdWeight;
	private double bmi;
	private String bmiResult;
}
