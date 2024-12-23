package net.scit.sec.entity;

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
import net.scit.sec.dto.UserDTO;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name="tbl_user")
public class UserEntity {
	
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seq;
	
	@Column(name="user_id", unique = true)
	private String userId;
	
	@Column(name="user_pwd", nullable = false)
	private String userPwd;
	
	@Column(name="user_name", nullable = false)
	private String userName;
	
	@Builder.Default
	@ColumnDefault("ROLE_USER")
	@Column(name="role")
	private String role = "ROLE_USER";
	
	//dto -> entity
	public static UserEntity toEntity(UserDTO userDTO) {
		//엔티티 자기 자신 반환(객체로 만들어서 반환)
		//role을 넣으면 오히려 에러
		//데이터가 안넘어왔을때 builder.default가 함
		return UserEntity.builder()
				.seq(userDTO.getSeq())
				.userId(userDTO.getUserId())
				.userPwd(userDTO.getUserPwd())
				.userName(userDTO.getUserName())
				.build();
	}
}
