package net.scit.carsharing.entity;

import org.hibernate.annotations.ColumnDefault;

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
import net.scit.carsharing.dto.UserDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="sharing_user")
public class UserEntity {
	@Id
	@Column(name="user_id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY) 타입이 integer 일때만 사용
	private String userId;
	
	@Column(name="user_pw", nullable = false)
	private String userPw;
	
	@Column(name="user_nm", nullable = false)
	private String userNm;
	
	@Builder.Default
	@ColumnDefault("ROLE_USER")
	@Column(name="roles")
	private String roles = "ROLE_USER";
	
	//dto -> entity
		public static UserEntity toEntity(UserDTO userDTO) {
			return UserEntity.builder()
					.userId(userDTO.getUserId())
					.userPw(userDTO.getUserPw())
					.userNm(userDTO.getUserNm())
					.build();
		}
}
