package net.scit.hrjoin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name="employees")
public class EmployeeEntity {
	
	@Id
	@Column(name="employee_id")
	private Long employeeId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="salary")
	private Double salary;
	
	// Has A, Is A ..?
	@ManyToOne	//departmententity가 one 이것이 아버지
	@JoinColumn(name="department_id", referencedColumnName = "department_id")
	private DepartmentEntity department;
}
