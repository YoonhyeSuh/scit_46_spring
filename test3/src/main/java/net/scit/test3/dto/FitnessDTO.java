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
	
	public void setHeight(double height) {//키를 입력받고 나서 계산
		this.height = height;
		calcStdWeight();
		calcBmi();
	}
	
	public void setWeight(double weight) {//몸무게를 입력받고 나서 계산
		this.weight = weight;
		calcStdWeight();
		calcBmi();
	}
	
	//표준체중, bmi, 결과
	//사용자 정의 메소드
	private void calcStdWeight() {
		double temp = this.height / 100;	//cm -> m로 환산
		
		if(this.gender) {//gender가 남자
			this.stdWeight = temp * temp * 22;	//남성의 표준체중
		} else {//gender가 여자
			this.stdWeight = temp * temp * 21;	//여성의 표준체중
		}
	}
	
	private void calcBmi() {
		double temp = this.height / 100;
		this.bmi = this.weight / (temp * temp);
		calcBmiResult();
	}
	
	private void calcBmiResult() {
		if(this.bmi >= 35) this.bmiResult = "고도 비만";
		else if(this.bmi >= 30) this.bmiResult = "중도 비만";
		else if(this.bmi >= 25) this.bmiResult = "경도 비만";
		else if(this.bmi >= 23) this.bmiResult = "과체중";
		else if(this.bmi >= 18.5) this.bmiResult = "정상";
		else this.bmiResult = "저체중";
	}
}
